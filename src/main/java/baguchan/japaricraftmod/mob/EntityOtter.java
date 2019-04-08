package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.mob.ai.EntityAIFriendAttackMelee;
import baguchan.japaricraftmod.mob.ai.EntityAIFriendCollectItem;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;


public class EntityOtter extends EntityFriend {

    public EntityOtter(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.6F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIFriendAttackMelee(this, 1.1D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIFriendCollectItem(this, 1.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 9.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    protected float getWaterSlowDown() {
        return 0.98F;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
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

}
