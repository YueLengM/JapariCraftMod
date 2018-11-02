package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.handler.JapariItems;
import baguchan.japaricraftmod.mob.ai.EntityAIFriendCollectItem;
import baguchan.japaricraftmod.mob.ai.EntityAIPlayWithFriend;
import baguchan.japaricraftmod.mob.ai.EntityAIStopPlayFollowOwner;
import com.google.common.collect.Sets;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Set;

public class EntityAraisan extends EntityPlayFriend {

    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);

    public EntityAraisan(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.6F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }


    protected void initEntityAI() {
        super.initEntityAI();
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIPanic(this, 1.4D));
        this.tasks.addTask(2, new EntityAIAvoidEntity<>(this, EntityCerulean.class, 6.5F, 1.1D, 1.1D));
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIStopPlayFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIFriendCollectItem(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIPlayWithFriend(this, 1.05D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(26D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }


    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public Item getDropItem() {

        return null;//なにも落とさない
    }


    @Override
    public boolean canDespawn() {
        return false;
    }


}
