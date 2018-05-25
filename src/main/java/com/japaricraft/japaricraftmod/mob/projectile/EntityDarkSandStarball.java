package com.japaricraft.japaricraftmod.mob.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDarkSandStarball extends EntityFireball {
    public int explosionPower = 1;

    public EntityDarkSandStarball(World worldIn) {
        super(worldIn);
        this.setSize(0.4F, 0.4F);
    }

    @SideOnly(Side.CLIENT)
    public EntityDarkSandStarball(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.4F, 0.4F);
    }

    public EntityDarkSandStarball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.4F, 0.4F);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 4.0F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }

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

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
            this.world.newExplosion((Entity) null, this.posX, this.posY, this.posZ, (float) this.explosionPower, false, flag);
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
        compound.setInteger("ExplosionPower", this.explosionPower);
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