package com.japaricraft.japaricraftmod.mob;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nonnull;

public class EntitySandStarSlime extends EntitySlime {
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(JapariCraftMod.MODID, "entitys/sandstarslime");

    public EntitySandStarSlime(World worldIn) {
        super(worldIn);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return this.getSlimeSize() == 1 ? LOOT_TABLE : LootTableList.EMPTY;
    }

    @Nonnull
    @Override
    protected EntitySlime createInstance() {
        return new EntitySandStarSlime(this.getEntityWorld());
    }

    @Override
    protected void updateAITasks() {
        if (this.ticksExisted % 5 == 0) {
            this.heal(0.04F);
        }
    }
}
