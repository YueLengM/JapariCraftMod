package com.japaricraft.japaricraftmod;


import com.japaricraft.japaricraftmod.world.MapGenSandStarRuin;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructure;

public class CommonProxy{

    public void registerItemSided(Item item) {
    }
    public void init(){

    }

    public void registerFluidBlockRendering(Block fluidBlock, String name) {
    }

    public MapGenSandStarRuin chest = new MapGenSandStarRuin();

    public MapGenStructure getTreasureMapGen() {
        return chest;
    }
}
