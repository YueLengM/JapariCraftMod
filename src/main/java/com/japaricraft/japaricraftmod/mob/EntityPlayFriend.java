package com.japaricraft.japaricraftmod.mob;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityPlayFriend extends EntityFriend {
    private static final DataParameter<Boolean> PLAYING = EntityDataManager.<Boolean>createKey(EntityPlayFriend.class, DataSerializers.BOOLEAN);
    private boolean isPlaying;

    //特定のフレンズを遊ばせる
    protected EntityPlayFriend(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(PLAYING, false);

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Playing", this.isPlaying());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setPlaying(compound.getBoolean("Playing"));
    }

    public boolean isPlaying() {
        if (world.isRemote) {
            boolean isPlaying = this.dataManager.get(PLAYING);
            this.isPlaying = isPlaying;
            return isPlaying;
        }
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        this.dataManager.set(PLAYING, playing);
        if (!world.isRemote) {
            this.isPlaying = playing;
        }
    }
}
