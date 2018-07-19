package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.world.biome.JapariBiomes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
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
    public boolean getCanSpawnHere() {
        BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));
        Chunk chunk = this.world.getChunkFromBlockCoords(blockpos);

        if (this.world.getWorldInfo().getTerrainType().handleSlimeSpawnReduction(rand, world)) {
            return false;
        } else {
            if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
                Biome biome = this.world.getBiome(blockpos);

                if (biome == JapariBiomes.SAND_STAR_ISLAND && this.posY > 50.0D && this.posY < 70.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < this.world.getCurrentMoonPhaseFactor() && this.world.getLightFromNeighbors(new BlockPos(this)) <= this.rand.nextInt(8)) {
                    return super.getCanSpawnHere();
                }

                if (this.rand.nextInt(10) == 0 && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D) {
                    return super.getCanSpawnHere();
                }
            }

            return false;
        }
    }

    @Override
    protected void updateAITasks() {
        if (this.ticksExisted % 5 == 0) {
            this.heal(0.04F);
        }
    }
}
