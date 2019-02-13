package baguchan.japaricraftmod.handler;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Calendar;

public class JapariUtil {
    public static boolean isApril(World world) {
        return world.getCurrentDate().get(Calendar.MONTH) + 1 == 4 &&
                world.getCurrentDate().get(Calendar.DATE) == 1;
        //return true;
    }

    public static BlockPos getHeight(World world, BlockPos pos) {

        for (int y = 0; y < 256; y++) {

            BlockPos pos1 = pos.up(y);

            if (world.getBlockState(pos1.up()).getBlock() == Blocks.AIR && world.getBlockState(pos1.down()).getBlock() != Blocks.AIR) {

                return pos1;

            }

        }

        return pos;

    }
}
