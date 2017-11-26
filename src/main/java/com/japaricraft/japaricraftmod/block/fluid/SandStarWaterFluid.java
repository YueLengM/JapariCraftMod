package com.japaricraft.japaricraftmod.block.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class SandStarWaterFluid extends Fluid {

    public static final String name = "sandstar";
    public static final SandStarWaterFluid instance = new SandStarWaterFluid();

    public SandStarWaterFluid() {
        super(name, new ResourceLocation("japaricraftmod:blocks/sandstarwater_still"), new ResourceLocation("japaricraftmod:blocks/sandstarwater_flow"));
    }

}