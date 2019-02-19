package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.handler.JapariItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockWoodenBox extends Block {
    public BlockWoodenBox() {
        super(Material.WOOD);
        setUnlocalizedName("WoodenBox");
        setHardness(0.2F);
        setResistance(0.4F);
        setSoundType(SoundType.WOOD);
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (rand.nextInt(5) == 0){
            return Items.SUGAR;
        }
        else if (rand.nextInt(7) == 0){
            return Items.IRON_NUGGET;
        }
        else if (rand.nextInt(10) == 0){
            return Items.GOLD_NUGGET;
        } else if (rand.nextInt(10) == 0) {
            return JapariItems.japariman;
        } else if (rand.nextInt(12) == 0) {
            return JapariItems.japarimanapple;
        } else if (rand.nextInt(25) == 0){
            return JapariItems.sandstarfragment;
        } else{
            return Items.BONE;
        }
    }
    public int quantityDropped(Random random)
    {
        return 1 + random.nextInt(4);
    }
}
