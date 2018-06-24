package com.japaricraft.japaricraftmod.mob;

import com.google.common.collect.Sets;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.advancements.AchievementsJapari;
import com.japaricraft.japaricraftmod.gui.FriendMobNBTs;
import com.japaricraft.japaricraftmod.gui.InventoryFriendEquipment;
import com.japaricraft.japaricraftmod.gui.InventoryFriendMain;
import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityFriend extends EntityTameable {
    private static final Set<Item> Heal_ITEMS = Sets.newHashSet(JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);

    protected static final DataParameter<Float> dataEXPValue = EntityDataManager.createKey(EntityFriend.class, DataSerializers.FLOAT);

    private InventoryFriendMain inventoryFriendMain;
    private InventoryFriendEquipment inventoryFriendEquipment;
    public float friendPoint = 0;

    protected EntityFriend(World worldIn) {
        super(worldIn);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(EntityFriend.dataEXPValue, Float.valueOf(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        compound.setTag(FriendMobNBTs.ENTITY_FRIEND_INVENTORY, this.getInventoryFriendMain().writeInventoryToNBT());

        compound.setTag(FriendMobNBTs.ENTITY_FRIEND_EQUIPMENT, this.getInventoryFriendEquipment().writeInventoryToNBT());

        compound.setFloat(JapariCraftMod.MODID + ":FRIEND_EXP", experienceValue);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.getInventoryFriendMain().readInventoryFromNBT(compound.getTagList(FriendMobNBTs.ENTITY_FRIEND_INVENTORY, 10));

        this.getInventoryFriendEquipment().readInventoryFromNBT(compound.getTagList(FriendMobNBTs.ENTITY_FRIEND_EQUIPMENT, 10));

        friendPoint = compound.getFloat(JapariCraftMod.MODID + ":FRIEND_EXP");

        dataManager.set(EntityFriend.dataEXPValue, friendPoint);
    }

    @Override
    public void onUpdate() {
        if (world.isRemote) {
            if (ticksExisted % 10 == 0) {
                friendPoint = dataManager.get(EntityFriend.dataEXPValue);
            }
        }
        super.onUpdate();
    }

    public float getExp() {
        return friendPoint;
    }

    /**
     * フレンズの経験値関係
     */
    public void addExperience(float value) {
        friendPoint += value;
        if (friendPoint >= 140) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getMaxHealth() + 2.0D);
            this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() + 1.0D);
            this.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, this.getSoundVolume(), 1.2F);
            friendPoint = 0;
        }
        dataManager.set(EntityFriend.dataEXPValue, friendPoint);
    }

    @Override
    public void damageArmor(float pDamage) {
        pDamage = Math.max(pDamage / 4, 1);

        if (inventoryFriendEquipment.getbootItem() != null && inventoryFriendEquipment.getbootItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getbootItem().damageItem((int) pDamage, this);
        }
        if (inventoryFriendEquipment.getChestItem() != null && inventoryFriendEquipment.getChestItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getChestItem().damageItem((int) pDamage, this);
        }
        if (inventoryFriendEquipment.getheadItem() != null && inventoryFriendEquipment.getheadItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getheadItem().damageItem((int) pDamage, this);
        }
        if (inventoryFriendEquipment.getLegItem() != null && inventoryFriendEquipment.getLegItem().getItem() instanceof ItemArmor) {
            inventoryFriendEquipment.getLegItem().damageItem((int) pDamage, this);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.updateArmSwingProgress();
        if (!world.isRemote) {
            if (this.isEntityAlive()) {
                pickupItem();
            }
            //やばい時はじゃぱりまんを食べる
            if (getHealth() < getMaxHealth() / 1.8 && this.rand.nextInt(20) == 0) {
                eatJapariman();
            }
        }
    }

    /*
     * 右クリック時の処理
     */
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (this.isTamed()) {
            if (player.isSneaking() && !this.isSitting()) {
                player.openGui(JapariCraftMod.instance, JapariCraftMod.ID_JAPARI_INVENTORY, this.getEntityWorld(), this.getEntityId(), 0, 0);
            }
            if (!stack.isEmpty()) {
                //デバッグ用
                if (this.isOwner(player) && stack.getItem() == Items.STICK) {
                    float i = friendPoint;
                    String s = String.valueOf(i);
                    player.sendStatusMessage(new TextComponentTranslation(s + "exp"), true);
                }
                if (this.isOwner(player) && Heal_ITEMS.contains(stack.getItem())) {
                    ItemFood itemfood = (ItemFood) stack.getItem();
                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                        }

                        this.heal((float) itemfood.getHealAmount(stack));
                        this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());

                        for (int i = 0; i < 7; ++i) {
                            double d0 = this.rand.nextGaussian() * 0.02D;
                            double d1 = this.rand.nextGaussian() * 0.02D;
                            double d2 = this.rand.nextGaussian() * 0.02D;
                            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
                        }
                        return true;
                    }
                }
                if (this.isOwner(player) && stack.getItem() == JapariItems.wildliberationpotion) {

                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }
                    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getMaxHealth() + 4.0D);
                    this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, this.getSoundVolume(), this.getSoundPitch());

                    for (int i = 0; i < 7; ++i) {
                        double d0 = this.rand.nextGaussian() * 0.02D;
                        double d1 = this.rand.nextGaussian() * 0.02D;
                        double d2 = this.rand.nextGaussian() * 0.02D;
                        this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.8D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
                    }
                    return true;
                }
            }
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack)) {
                this.aiSit.setSitting(!this.isSitting());
                return true;
            }
        } else if (!this.isTamed() && Heal_ITEMS.contains(stack.getItem())) {
            if (!player.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                    //ここで実績を解除させる
                    AchievementsJapari.grantAdvancement(player, "tame_friends");
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }


            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        addExperience(1 + rand.nextInt(2));
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }


    //インベントリにじゃぱりまんがあるか確認する処理

    private void eatJapariman() {
        ItemStack itemstack = findFood();

        if (!itemstack.isEmpty()) {
            //じゃぱりまんがあるか確認

            ItemFood itemfood = (ItemFood) itemstack.getItem();
            this.heal((float) itemfood.getHealAmount(itemstack));
            itemstack.shrink(1);
            this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    /**
     * インベントリ内の食べれる食べ物を探す.
     */
    private ItemStack findFood() {
        ItemStack friendsstack;

        for (int i = 0; i < this.getInventoryFriendMain().getSizeInventory(); ++i) {
            friendsstack = getInventoryFriendMain().getStackInSlot(i);

            if (Heal_ITEMS.contains(friendsstack.getItem())) {
                return friendsstack;
            }
        }
        return ItemStack.EMPTY;
    }

    private void pickupItem() {
        if (ticksExisted % 10 != 0) {
            return;
        }

        for (EntityItem entityItem : world.getEntitiesWithinAABB(EntityItem.class, getEntityBoundingBox().grow(0.65D))) {
            if (entityItem.isEntityAlive() && entityItem.onGround) {
                ItemStack stack = entityItem.getItem();

                if (!stack.isEmpty()) {
                    stack = onItemStackPickup(stack);

                    if (stack.isEmpty()) {
                        entityItem.setDead();

                        playPickupSound();
                    } else {
                        entityItem.setItem(stack);
                    }
                }
                break;
            }
        }
    }

    public ItemStack onItemStackPickup(ItemStack stack) {
        return getInventoryFriendMain().addItem(stack);
    }

    protected void playPickupSound() {
        playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.25F, 0.85F);
    }


    public InventoryFriendMain getInventoryFriendMain() {
        if (this.inventoryFriendMain == null) {
            this.inventoryFriendMain = new InventoryFriendMain(this);
        }

        return this.inventoryFriendMain;
    }

    public InventoryFriendEquipment getInventoryFriendEquipment() {
        if (this.inventoryFriendEquipment == null) {
            this.inventoryFriendEquipment = new InventoryFriendEquipment(this);
        }

        return this.inventoryFriendEquipment;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
        ItemStack itemStack;

        switch (slotIn) {
            case CHEST:

                itemStack = this.getInventoryFriendEquipment().getChestItem();
                break;
            case FEET:

                itemStack = this.getInventoryFriendEquipment().getbootItem();
                break;
            case HEAD:

                itemStack = this.getInventoryFriendEquipment().getheadItem();
                break;
            case LEGS:

                itemStack = this.getInventoryFriendEquipment().getLegItem();
                break;
            case MAINHAND:

                itemStack = this.getInventoryFriendEquipment().getHandItem();
                break;

            default:

                itemStack = ItemStack.EMPTY;
                break;
        }

        return itemStack;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        switch (slotIn) {

            case CHEST:

                this.getInventoryFriendEquipment().setInventorySlotContents(0, stack);
                break;
            case FEET:

                this.getInventoryFriendEquipment().setInventorySlotContents(1, stack);
                break;
            case HEAD:

                this.getInventoryFriendEquipment().setInventorySlotContents(2, stack);
                break;
            case LEGS:

                this.getInventoryFriendEquipment().setInventorySlotContents(3, stack);
                break;
            case MAINHAND:

                this.getInventoryFriendEquipment().setInventorySlotContents(4, stack);
                break;
        }
    }

    //死んだ時に持ってるアイテム落とす
    @Override
    public void onDeath(DamageSource cause) {
        World world = this.getEntityWorld();

        if (!world.isRemote) {
            InventoryHelper.dropInventoryItems(world, this, this.getInventoryFriendMain());

            InventoryHelper.dropInventoryItems(world, this, this.getInventoryFriendEquipment());
        }

        super.onDeath(cause);
    }

    //ここから下は体力バーの表示方法
    public EntityFriend.Condition getCondition() {
        int health = (int) this.getHealth();
        int healthMax = (int) this.getMaxHealth();

        EntityFriend.Condition condition = EntityFriend.Condition.FINE;

        if (health < (healthMax / 2)) {
            condition = EntityFriend.Condition.HURT;

            if (health < (healthMax / 4)) {
                condition = EntityFriend.Condition.DYING;
            }
        }

        return condition;
    }

    /**
     * バニラから引用、事故がないように
     */
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if (!(target instanceof EntityGhast)) {
            if (target instanceof EntityTameable) {
                EntityTameable entityTameable = (EntityTameable) target;
                if (entityTameable.isTamed() && entityTameable.getOwner() == owner) {
                    return false;
                }
            }
            if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
                return false;
            } else {
                return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
            }
        } else {
            return true;
        }
    }

    @Override
    protected void updateAITasks() {
        if (this.ticksExisted % 5 == 0) {
            this.heal(0.06F);
        }
    }


    /*
     * GUI用、体力がどれくらいあるかで色が変わる
     */
    public enum Condition {

        FINE,
        HURT,
        DYING,

    }
}
