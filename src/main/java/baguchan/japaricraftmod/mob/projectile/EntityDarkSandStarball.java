package baguchan.japaricraftmod.mob.projectile;

import baguchan.japaricraftmod.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.*;
import net.minecraft.nbt.*;
import net.minecraft.potion.*;
import net.minecraft.util.datafix.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

import javax.annotation.*;

public class EntityDarkSandStarball extends EntityFireball {
    public float explosionPower = 0.8F;

    public EntityDarkSandStarball(World worldIn) {
        super(worldIn);
        this.setSize(0.3F, 0.3F);
    }

    @SideOnly(Side.CLIENT)
    public EntityDarkSandStarball(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3F, 0.3F);
    }

    public EntityDarkSandStarball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3F, 0.3F);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {
        if (!this.world.isRemote) {
            Entity entity = result.entityHit;

            if (entity != null && !(entity instanceof EntityDarkSandStarball)) {
                result.entityHit.attackEntityFrom(JapariCraftMod.sandstarLow, 4.0F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);


                if (result.entityHit instanceof EntityLivingBase) {
                    int i = 0;

                    if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                        i = 6;
                    } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                        i = 8;
                    }

                    if (i > 0) {
                        ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 20 * i, 0));
                        ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.WITHER, 10 * i, 1));
                    }
                }
            }

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
            this.world.newExplosion(null, this.posX, this.posY, this.posZ, this.explosionPower, false, flag);
            this.setDead();
        }
    }


    @Override
    protected boolean isFireballFiery() {
        return false;
    }

    @Override
    public boolean isBurning() {
        return false;
    }

    public static void registerFixesCeruleanball(DataFixer fixer) {
        EntityFireball.registerFixesFireball(fixer, "Ceruleanball");
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setFloat("ExplosionPower", this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("ExplosionPower", 99)) {
            this.explosionPower = compound.getInteger("ExplosionPower");
        }
    }
}