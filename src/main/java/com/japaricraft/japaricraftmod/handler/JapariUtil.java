package com.japaricraft.japaricraftmod.handler;

import net.minecraft.world.World;

import java.util.Calendar;

public class JapariUtil {
    public static boolean isApril(World world) {
        return world.getCurrentDate().get(Calendar.MONTH) + 1 == 4 &&
                world.getCurrentDate().get(Calendar.DATE) == 1;
        //return true;
    }
}
