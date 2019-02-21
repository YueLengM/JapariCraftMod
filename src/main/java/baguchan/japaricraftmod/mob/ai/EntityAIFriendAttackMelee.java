package baguchan.japaricraftmod.mob.ai;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;

public class EntityAIFriendAttackMelee extends EntityAIAttackMelee {
    public EntityAIFriendAttackMelee(EntityCreature creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
        this.setMutexBits(3);
    }

    @Override
    public void updateTask() {
        super.updateTask();

        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);

        //shift氏のやつを参考にしてみた（近づきすぎないようにする）
        if (entitylivingbase != null) {

            if (d0 <= 3.0d) {

                this.attacker.getNavigator().clearPath();

            }

        }

    }

    @Override
    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double) (this.attacker.width * 2.7F * this.attacker.width * 2.7F + attackTarget.width);
    }
}
