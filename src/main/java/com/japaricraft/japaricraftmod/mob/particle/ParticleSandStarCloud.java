package com.japaricraft.japaricraftmod.mob.particle;

import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSandStarCloud extends ParticleCloud {
    public ParticleSandStarCloud(World world, double xCoordIn, double yCoordIn, double zCoordIn, double v4, double v5) {
        super(world, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.8D, 0.0D);
        float colorMultiplier = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F * colorMultiplier;
        this.particleRed *= 0.08F;
        this.particleGreen *= 0.08F;

    }
}
