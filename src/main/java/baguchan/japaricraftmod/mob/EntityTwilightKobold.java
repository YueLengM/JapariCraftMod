package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.handler.JapariItems;
import baguchan.japaricraftmod.mob.ai.EntityAIFriendAttackMelee;
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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Set;

public class EntityTwilightKobold extends EntityFriend {

    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.APPLE, Items.WHEAT, JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);

    private static final DataParameter<Boolean> SCARED = EntityDataManager.createKey(EntityTwilightKobold.class, DataSerializers.BOOLEAN);

    public EntityTwilightKobold(World worldIn) {
        super(worldIn);
        this.setSize(0.59F, 1.6F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityTwilightKobold.AIScared(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIFriendAttackMelee(this, 1.12D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SCARED, Boolean.FALSE);
    }

    public boolean isScared() {
        return this.dataManager.get(SCARED);
    }

    public void setScared(boolean scared) {
        this.dataManager.set(SCARED, scared);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }


    @Override
    public boolean isHealItem(ItemStack stack) {
        return TAME_ITEMS.contains(stack.getItem());
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


    public boolean canDespawn() {
        return false;
    }


    private class AIScared extends EntityAIBase {
        private final EntityTwilightKobold scaredEntity;
        private final World world;
        private EntityPlayer player;

        public AIScared(EntityTwilightKobold scaredEntity) {
            this.setMutexBits(7);
            this.scaredEntity = scaredEntity;
            this.world = scaredEntity.world;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (!this.scaredEntity.isTamed() && this.scaredEntity.getAttackTarget() == null) {
                this.player = this.world.getClosestPlayerToEntity(this.scaredEntity, (double) 8.0F);
                return this.player != null && !this.player.isCreative() && !this.player.isSpectator() && this.scaredEntity.canEntityBeSeen(this.player);
            }
            return false;
        }

        public void startExecuting() {
            this.scaredEntity.setScared(true);
        }

        public void resetTask() {
            this.player = null;
            this.scaredEntity.setScared(false);
        }

        public void updateTask() {
            this.scaredEntity.getLookHelper().setLookPositionWithEntity(this.player, (float) (this.scaredEntity.getHorizontalFaceSpeed() + 20), (float) this.scaredEntity.getVerticalFaceSpeed());

            if (this.scaredEntity.getDistanceSq(this.player) < 8.0D) {
                this.scaredEntity.getNavigator().clearPath();
            }
        }
    }


}
