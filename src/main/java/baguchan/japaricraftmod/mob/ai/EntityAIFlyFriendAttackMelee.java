package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityFlyFriend;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.world.World;

public class EntityAIFlyFriendAttackMelee extends EntityAIAttackMelee {
    World world;
    protected EntityFlyFriend attacker;
    private int flytick;

    public EntityAIFlyFriendAttackMelee(EntityFlyFriend creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
        this.setMutexBits(3);
        this.attacker = creature;
        this.world = creature.world;
    }

    public void startExecuting() {
        super.startExecuting();
        this.flytick = 0;

    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.attacker.setFlying(false);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        //If she judge that can not cross a cliff, she will fly to this side
        if (this.attacker.getNavigator().noPath() || this.attacker.getNavigator().noPath() && entitylivingbase.posY - 8 > this.attacker.posY && this.flytick == 0 && this.world.rand.nextInt(20) == 0) {
            this.attacker.setFlying(true);
        }

        if (this.attacker.isFlying()) {
            ++this.flytick;
        }

        if (this.flytick >= 80 && this.world.rand.nextInt(120) == 0) {
            this.attacker.setFlying(false);

            this.flytick = 0;
        }

        this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, (float) this.attacker.getHorizontalFaceSpeed(), (float) this.attacker.getVerticalFaceSpeed());
    }
}
