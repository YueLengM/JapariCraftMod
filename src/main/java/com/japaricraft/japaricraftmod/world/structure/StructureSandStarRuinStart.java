package com.japaricraft.japaricraftmod.world.structure;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.List;
import java.util.Random;

public class StructureSandStarRuinStart extends StructureStart {

    public StructureSandStarRuinStart(World par1World, Random par2Random, int par3, int par4) {
        super(par3, par4);

        // 構造物の構成パーツを決定する
        ComponentSandStarRuin componentHumanHouse1 = new ComponentSandStarRuin(0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2);
        this.components.add(componentHumanHouse1);
        // 次のパーツを得る
        componentHumanHouse1.buildComponent(componentHumanHouse1, components, par2Random);

        List<StructureComponent> list = componentHumanHouse1.structureComponents;
        while (!list.isEmpty()) {
            int k = par2Random.nextInt(list.size());
            StructureComponent structurecomponent = list.remove(k);
            structurecomponent.buildComponent(componentHumanHouse1, this.components, par2Random);
        }
        // 構造物全体の占有範囲を更新する
        this.updateBoundingBox();
    }
}