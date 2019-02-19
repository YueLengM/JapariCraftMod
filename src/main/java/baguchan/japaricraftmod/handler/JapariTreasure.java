package baguchan.japaricraftmod.handler;


import baguchan.japaricraftmod.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.properties.*;
import net.minecraftforge.fml.relauncher.*;

import java.lang.reflect.*;
import java.util.*;

public class JapariTreasure {
    public static final ResourceLocation humanhouse = register("chest/humanhouse");
    public static final ResourceLocation cafe = register("chest/cafe");
    public static final ResourceLocation lab = register("chest/sandstarlab");
    public static final ResourceLocation starsorcerger = register("entitys/starsorcerger");
    public static final ResourceLocation scocel = register("entitys/scocel");
    private ResourceLocation lootTable;

    private static ResourceLocation register(String id) {
        return LootTableList.register(new ResourceLocation(JapariCraftMod.MODID, id));
    }

    private static ResourceLocation register(EntityProperty.Serializer<?> serializer) {
        EntityPropertyManager.registerProperty(serializer);
        return serializer.getName();
    }

    public static ArrayList<ItemStack> getItemsFromTable(ResourceLocation lootTable, World world) {
        ArrayList<ItemStack> items = new ArrayList<>();

        LootTable table = world.getLootTableManager().getLootTableFromLocation(lootTable);
        LootContext.Builder lootBuilder = (new LootContext.Builder((WorldServer) world));
        Field f = ReflectionHelper.findField(LootTable.class, "pools", "field_186466_c", "c");
        List<LootPool> pools = null;
        try {
            pools = (List<LootPool>) f.get(table);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (pools != null && pools.size() > 0) {
            for (LootPool pool : pools) {
                Field f2 = ReflectionHelper.findField(LootPool.class, "lootEntries", "field_186453_a", "a");
                List<LootEntry> entries = null;
                try {
                    entries = (List<LootEntry>) f2.get(pool);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (entries != null && entries.size() > 0) {
                    for (LootEntry entry:entries) {
                        if (entry instanceof LootEntryItem) {
                            entry.addLoot(items, new Random(), lootBuilder.build());
                        }
                    }
                }
            }
        }
        return items;
    }
}
