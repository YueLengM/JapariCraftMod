package com.japaricraft.japaricraftmod.handler;

import com.google.common.collect.Maps;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.Map;
import java.util.Random;

public class ModVillagers {
    public static final ModVillagers INSTANCE = new ModVillagers();
    public static VillagerRegistry.VillagerProfession japariProfession;
    public Map<Integer, VillagerRegistry.VillagerProfession> professions = Maps.newHashMap();

    public static final ResourceLocation PROFESSIONS = new ResourceLocation("japaricraftmod:tutinokoprofessions");
    public VillagerRegistry.VillagerProfession raretreader;
    public VillagerRegistry.VillagerProfession armorcrafter;
    public VillagerRegistry.VillagerProfession foodcrafter;

    public void init() {
        raretreader = new VillagerRegistry.VillagerProfession("japaricraftmod:raretreader", "minecraft:textures/entity/villager/farmer.png", "minecraft:textures/entity/zombie_villager/zombie_farmer.png");
        {
            VillagerRegistry.VillagerCareer career = new VillagerRegistry.VillagerCareer(raretreader, "raretreader");
            career.addTrade(1, new CoinForItems(Items.COAL, new EntityVillager.PriceInfo(7, 12)));
            career.addTrade(2, new ListItemForCoin(Items.IRON_HORSE_ARMOR, new EntityVillager.PriceInfo(4, 6)));
            career.addTrade(2, new ListItemForCoin(Items.GOLDEN_APPLE, new EntityVillager.PriceInfo(2, 4)));
            career.addTrade(3, new ListItemForCoin(Items.ENDER_PEARL, new EntityVillager.PriceInfo(1, 3)));
            register(raretreader, 0);
        }
        armorcrafter = new VillagerRegistry.VillagerProfession("japaricraftmod:armorcrafter", "minecraft:textures/entity/villager/farmer.png", "minecraft:textures/entity/zombie_villager/zombie_farmer.png");
        {
            VillagerRegistry.VillagerCareer career = new VillagerRegistry.VillagerCareer(armorcrafter, "armorcrafter");

            career.addTrade(1, new CoinForItems(Item.getItemFromBlock(Blocks.LOG), new EntityVillager.PriceInfo(20, 30)));
            career.addTrade(1, new ListItemForCoin(JapariItems.kabanhat, new EntityVillager.PriceInfo(1, 3)));
            career.addTrade(2, new ListItemForCoin(Items.SADDLE, new EntityVillager.PriceInfo(1, 3)));
            career.addTrade(2, new ListItemForCoin(Items.LEATHER_CHESTPLATE, new EntityVillager.PriceInfo(1, 3)));
            career.addTrade(2, new ListItemForCoin(Items.LEATHER_LEGGINGS, new EntityVillager.PriceInfo(1, 3)));
            register(armorcrafter, 1);
        }
        foodcrafter = new VillagerRegistry.VillagerProfession("japaricraftmod:foodcrafter", "minecraft:textures/entity/villager/farmer.png", "minecraft:textures/entity/zombie_villager/zombie_farmer.png");
        {
            VillagerRegistry.VillagerCareer career = new VillagerRegistry.VillagerCareer(foodcrafter, "foodcrafter");
            career.addTrade(1, new CoinForItems(Items.WHEAT, new EntityVillager.PriceInfo(10, 15)));
            career.addTrade(1, new CoinForItems(Items.SUGAR, new EntityVillager.PriceInfo(10, 14)));
            career.addTrade(2, new ListItemForCoin(JapariItems.starjapariman, new EntityVillager.PriceInfo(2, 5)));
            register(foodcrafter, 2);
        }

        japariProfession = new VillagerRegistry.VillagerProfession(JapariCraftMod.MODID + ":zookeeper", "japaricraftmod:textures/entity/zookeeper.png", "japaricraftmod:textures/entity/zookeeper_zombie.png");
        ForgeRegistries.VILLAGER_PROFESSIONS.register(japariProfession);
        VillagerRegistry.VillagerCareer career_zookeeper = new VillagerRegistry.VillagerCareer(japariProfession, "zookeeper");
        career_zookeeper.addTrade(1,
                new EntityVillager.EmeraldForItems(Items.WHEAT, new EntityVillager.PriceInfo(18, 22)),
                new EntityVillager.EmeraldForItems(Items.APPLE, new EntityVillager.PriceInfo(12, 18)),
                new EntityVillager.EmeraldForItems(Items.SUGAR, new EntityVillager.PriceInfo(14, 19)),
                new EntityVillager.EmeraldForItems(Items.CARROT, new EntityVillager.PriceInfo(15, 19))
        );
        career_zookeeper.addTrade(2,
                new EntityVillager.ListItemForEmeralds(JapariItems.japariman, new EntityVillager.PriceInfo(-8, -10)),
                new EntityVillager.ListItemForEmeralds(JapariItems.japarimanapple, new EntityVillager.PriceInfo(-8, -10)),
                new EntityVillager.ListItemForEmeralds(JapariItems.kabanhat, new EntityVillager.PriceInfo(1, 2)),
                new EntityVillager.ListItemForEmeralds(JapariItems.japarimancocoa, new EntityVillager.PriceInfo(-8, -10))
        );
        career_zookeeper.addTrade(3,
                new EntityVillager.EmeraldForItems(Items.SLIME_BALL, new EntityVillager.PriceInfo(8, 14)),
                new EntityVillager.ListItemForEmeralds(JapariItems.wildliberationpotion, new EntityVillager.PriceInfo(6, 8)),
                new EntityVillager.ListItemForEmeralds(JapariItems.starjapariman, new EntityVillager.PriceInfo(6, 8)));
    }

    public void setRandomProfession(EntityVillager entity, Random rand) {
        entity.setProfession(professions.get(rand.nextInt(professions.size())));
    }

    private void register(VillagerRegistry.VillagerProfession prof, int id) {
        professions.put(id, prof);
    }

    /**
     * Sell items for sapphires
     */
    public static class CoinForItems implements EntityVillager.ITradeList {
        /**
         * The item that is being sold for emeralds
         */
        public Item buyingItem;
        public EntityVillager.PriceInfo price;

        public CoinForItems(Item itemIn, EntityVillager.PriceInfo priceIn) {
            this.buyingItem = itemIn;
            this.price = priceIn;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            int i = 1;
            if (this.price != null) {
                i = this.price.getPrice(random);
            }
            recipeList.add(new MerchantRecipe(new ItemStack(this.buyingItem, i, 0), JapariItems.japaricoin));
        }
    }

    /**
     * Buy items for sapphires
     */
    public static class ListItemForCoin implements EntityVillager.ITradeList {
        /**
         * The item that is being bought for emeralds
         */
        public ItemStack itemToBuy;
        public EntityVillager.PriceInfo priceInfo;

        public ListItemForCoin(Item par1Item, EntityVillager.PriceInfo priceInfo) {
            this.itemToBuy = new ItemStack(par1Item);
            this.priceInfo = priceInfo;
        }

        public ListItemForCoin(ItemStack stack, EntityVillager.PriceInfo priceInfo) {
            this.itemToBuy = stack;
            this.priceInfo = priceInfo;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            int i = 1;

            if (this.priceInfo != null) {
                i = this.priceInfo.getPrice(random);
            }

            ItemStack itemstack;
            ItemStack itemstack1;
            if (i < 0) {
                itemstack = new ItemStack(JapariItems.japaricoin);
                itemstack1 = new ItemStack(this.itemToBuy.getItem(), -i, this.itemToBuy.getMetadata());
            } else {
                itemstack = new ItemStack(JapariItems.japaricoin, i, 0);
                itemstack1 = new ItemStack(this.itemToBuy.getItem(), 1, this.itemToBuy.getMetadata());
            }
            recipeList.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }
}
