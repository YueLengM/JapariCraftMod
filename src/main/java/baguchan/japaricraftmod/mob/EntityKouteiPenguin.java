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
import net.minecraft.world.World;

import java.util.Set;

public class EntityKouteiPenguin extends EntityFriend {

    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.FISH, Items.COOKED_FISH, JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);

    public EntityKouteiPenguin(World worldIn) {
        super(worldIn);
        this.setSize(0.59F, 1.7F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.05D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 11.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
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
    public boolean attackEntityAsMob(Entity entityIn) {
        addExperience(1 + rand.nextInt(2));
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }


    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }


    public Item getDropItem() {

        return null;//なにも落とさない
    }

    @Override
    protected void dropFewItems(boolean parRecentlyHit, int parLootingLevel) {
        {
            this.entityDropItem(new ItemStack(Items.FEATHER, 2, 0), 0.0F);

        }
    }

    public boolean canDespawn() {
        return false;
    }

}
