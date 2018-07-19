package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.handler.JapariItems;
import baguchan.japaricraftmod.mob.ai.EntityAITutinokoTradePlayer;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.Random;

public class EntityTutinoko extends EntityCreature implements INpc, IMerchant {

    private MerchantRecipeList buyingList;
    private EntityPlayer buyingPlayer;

    private static final EntityTutinoko.ITradeList[] DEFAULT_TRADE_LIST_MAP = new EntityTutinoko.ITradeList[]{new EntityTutinoko.JapariCoinForItems(Items.IRON_INGOT, new EntityTutinoko.PriceInfo(3, 6)), new EntityTutinoko.JapariCoinForItems(Items.GOLD_INGOT, new EntityTutinoko.PriceInfo(1, 3)), new EntityTutinoko.ListItemForJapariCoin(Items.SKULL, new EntityTutinoko.PriceInfo(2, 3)), new EntityTutinoko.ListItemForJapariCoin(Items.ENDER_EYE, new EntityTutinoko.PriceInfo(-3, -1))};

    public EntityTutinoko(World worldIn) {
        super(worldIn);
        this.experienceValue = 0;
        this.setSize(0.48F, 1.7F);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAITutinokoTradePlayer(this));
        tasks.addTask(2, new EntityAIMoveIndoors(this));
        tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        tasks.addTask(4, new EntityAIOpenDoor(this, true));
        tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.9D));
        tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.9D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        if (this.buyingList != null) {
            compound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("Offers", 10)) {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound);
        }
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (this.isEntityAlive()) {
            if (this.buyingList == null) {
                this.populateBuyingList();
            }

            if (!this.world.isRemote && !this.buyingList.isEmpty()) {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }

            return true;
        }
        return super.processInteract(player, hand);
    }

    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        return this.finalizeMobSpawn(difficulty, livingdata, true);
    }

    public IEntityLivingData finalizeMobSpawn(DifficultyInstance p_190672_1_, IEntityLivingData data, boolean p_190672_3_) {
        data = super.onInitialSpawn(p_190672_1_, data);

        this.populateBuyingList();
        return data;
    }

    @Override
    public void setCustomer(EntityPlayer player) {
        this.buyingPlayer = player;
    }

    @Override
    public EntityPlayer getCustomer() {
        return this.buyingPlayer;
    }

    public boolean isTrading() {
        return this.buyingPlayer != null;
    }

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player) {
        if (this.buyingList == null) {
            this.populateBuyingList();
        }

        return this.buyingList;
    }

    private void populateBuyingList() {
        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }
        EntityTutinoko.ITradeList[] trades = DEFAULT_TRADE_LIST_MAP;
        if (trades != null) {
            for (EntityTutinoko.ITradeList EntityTutinoko$itradelist : trades) {
                EntityTutinoko$itradelist.addMerchantRecipe(this, this.buyingList, this.rand);
            }
        }
    }

    @Override
    public void setRecipes(MerchantRecipeList recipeList) {

    }

    @Override
    public void useRecipe(MerchantRecipe recipe) {

    }

    @Override
    public void verifySellingItem(ItemStack stack) {

    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public BlockPos getPos() {
        return new BlockPos(this);
    }

    public interface ITradeList {
        void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random);
    }

    public static class ListItemForJapariCoin implements EntityTutinoko.ITradeList {
        /**
         * The item that is being bought for emeralds
         */
        public ItemStack itemToBuy;
        /**
         * The price info for the amount of emeralds to sell for, or if negative, the amount of the item to buy for
         * an emerald.
         */
        public EntityTutinoko.PriceInfo priceInfo;

        public ListItemForJapariCoin(Item par1Item, EntityTutinoko.PriceInfo priceInfo) {
            this.itemToBuy = new ItemStack(par1Item);
            this.priceInfo = priceInfo;
        }

        public ListItemForJapariCoin(ItemStack stack, EntityTutinoko.PriceInfo priceInfo) {
            this.itemToBuy = stack;
            this.priceInfo = priceInfo;
        }

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

    public static class JapariCoinForItems implements EntityTutinoko.ITradeList {
        public Item buyingItem;
        public EntityTutinoko.PriceInfo price;

        public JapariCoinForItems(Item itemIn, EntityTutinoko.PriceInfo priceIn) {
            this.buyingItem = itemIn;
            this.price = priceIn;
        }

        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            int i = 1;

            if (this.price != null) {
                i = this.price.getPrice(random);
            }

            recipeList.add(new MerchantRecipe(new ItemStack(this.buyingItem, i, 0), JapariItems.japaricoin));
        }
    }

    public static class PriceInfo extends Tuple<Integer, Integer> {
        public PriceInfo(int p_i45810_1_, int p_i45810_2_) {
            super(p_i45810_1_, p_i45810_2_);
        }

        public int getPrice(Random rand) {
            return this.getFirst() >= this.getSecond() ? this.getFirst() : this.getFirst() + rand.nextInt(this.getSecond() - this.getFirst() + 1);
        }
    }

}