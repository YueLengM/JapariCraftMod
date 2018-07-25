package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.advancements.AchievementsJapari;
import baguchan.japaricraftmod.handler.JapariItems;
import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Set;

public class EntityBrownOwl extends EntityFriend {

    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(JapariItems.curry, Items.RABBIT_STEW, Items.MUSHROOM_STEW, JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    private float wingRotDelta = 1.0F;

    public EntityBrownOwl(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.1D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMoveIndoors(this));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityBlackCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));

    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    public boolean processInteractFood(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (this.isTamed()) {
            return false;
        }

        if (!this.world.isRemote) {
            if (TAME_ITEMS.contains(stack.getItem())) {
                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
                if (this.rand.nextInt(2) == 0) {
                    this.setTamed(true);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                    //ここで実績を解除させる
                    AchievementsJapari.grantAdvancement(player, "tame_friends");
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }
            }
        }
        return true;
    }

    @Override
    public double getYOffset() {
        return this.getRidingEntity() != null ? -0.40D : 0.0D;
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.calculateFlapping();
    }

    private void calculateFlapping() {
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < -0.3D) {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //フクロウが(いろんな意味で)乗ってる時に特定の操作で下ろす処理
        if (this.isRiding()) {
            final Entity entity = this.getRidingEntity();

            if (entity.isSneaking() && entity.onGround) {
                this.dismountRidingEntity();
            }

            if (entity.motionY < 0) {
                entity.motionY *= entity.isSneaking() ? 0.9D : 0.7D;

                entity.fallDistance = 0;
            }

        }

    }

    @Override
    public void updateRidden() {
        super.updateRidden();

        if (getRidingEntity() instanceof EntityPlayer) {
            EntityPlayer lep = (EntityPlayer) getRidingEntity();

            renderYawOffset = lep.renderYawOffset;
            prevRenderYawOffset = lep.prevRenderYawOffset;

            renderYawOffset = lep.renderYawOffset;
            if (((rotationYaw - renderYawOffset) % 360F) > 90F) {
                rotationYaw = renderYawOffset + 90F;
            }
            if (((rotationYaw - renderYawOffset) % 360F) < -90F) {
                rotationYaw = renderYawOffset - 90F;
            }
            if (((rotationYawHead - renderYawOffset) % 360F) > 90F) {
                rotationYawHead = renderYawOffset + 90F;
            }
            if (((rotationYawHead - renderYawOffset) % 360F) < -90F) {
                rotationYawHead = renderYawOffset - 90F;
            }
            double dx, dz;
            dx = Math.sin((lep.renderYawOffset * Math.PI) / 180D) * 0.35;
            dz = Math.cos((lep.renderYawOffset * Math.PI) / 180D) * 0.35;
            posX += dx;
            posZ -= dz;
        }
    }

    @Override
    public boolean canBeAttackedWithItem() {
        if (getRidingEntity() != null && getRidingEntity() == this.getOwner()) {
            return false;
        }
        return super.canBeAttackedWithItem();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        addExperience(1 + rand.nextInt(2));
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    public Item getDropItem() {

        return null;//なにも落とさない
    }

    @Override
    protected void dropFewItems(boolean parRecentlyHit, int parLootingLevel) {
        {
            this.entityDropItem(new ItemStack(Items.FEATHER, 2, 0), 0.0F);

        }
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

}
