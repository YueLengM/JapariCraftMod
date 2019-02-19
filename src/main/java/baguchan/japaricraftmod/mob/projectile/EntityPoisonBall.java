package baguchan.japaricraftmod.mob.projectile;

import baguchan.japaricraftmod.mob.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class EntityPoisonBall extends EntityThrowable {
    //ダメージ量
    private static final int DAMAGE = 2;


    public EntityPoisonBall(World world) {

        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityPoisonBall(World world, EntityLivingBase thrower) {

        super(world, thrower);
        this.setSize(0.5F, 0.5F);
    }

    @Override
    public void onUpdate() {

        super.onUpdate();

        makeTrail();
    }


    @Override

    protected float getGravityVelocity() {

        return 0.006F;

    }


    public void makeTrail() {

        for (int i = 0; i < 2; i++) {

            double dx = posX + 0.5 * (rand.nextDouble() - rand.nextDouble());

            double dy = posY + 0.5 * (rand.nextDouble() - rand.nextDouble());

            double dz = posZ + 0.5 * (rand.nextDouble() - rand.nextDouble());

            world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, dx, dy, dz, 0.0D, 0.0D, 0.0D);

        }

    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        super.attackEntityFrom(source, amount);

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();

        }

        return true;

    }


    @SideOnly(Side.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 3) {

            for (int j = 0; j < 8; ++j) {

                this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

            }

        } else {
            super.handleStatusUpdate(id);
        }

    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                if (!(result.entityHit instanceof EntityScocel) && result.entityHit instanceof EntityLivingBase) {
                    boolean flag = result.entityHit.attackEntityFrom((new EntityDamageSourceIndirect("indirectLowPoison", this, this.getThrower())), DAMAGE);

                    //When guarding with a shield, prevent poison
                    if (flag) {
                        this.applyEnchantments(this.thrower, result.entityHit);
                        ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0));
                    }


                }
            }
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }


    public void setDamage(int damage) {
        damage = DAMAGE;
    }
}