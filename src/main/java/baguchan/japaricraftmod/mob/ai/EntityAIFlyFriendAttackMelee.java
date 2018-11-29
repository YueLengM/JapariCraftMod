package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityFlyFriend;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.world.World;

public class EntityAIFlyFriendAttackMelee extends EntityAIAttackMelee {
    World world;
    protected EntityFlyFriend attacker;

    public EntityAIFlyFriendAttackMelee(EntityFlyFriend creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
        this.setMutexBits(3);
        this.attacker = creature;
        this.world = creature.world;
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
        if (!this.attacker.isInWater() && entitylivingbase.posY - 8 > this.attacker.posY && this.world.rand.nextInt(4) == 0) {
            this.attacker.setFlying(true);
        } else if (this.attacker.isInWater()) {
            this.attacker.setFlying(false);
        }

        if (entitylivingbase.posY > this.attacker.posY + 3 && this.world.rand.nextInt(30) == 0) {
            this.attacker.setFlying(false);
        }

        this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, (float) this.attacker.getHorizontalFaceSpeed(), (float) this.attacker.getVerticalFaceSpeed());
    }
}
