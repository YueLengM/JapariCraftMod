package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityPoisonCerulean extends EntityCerulean {
    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(JapariCraftMod.MODID, "entitys/cerulean");


    public EntityPoisonCerulean(World worldIn)
    {
        super(worldIn);
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 0;


                if (this.world.getDifficulty() == EnumDifficulty.EASY)
                {
                    i = 4;
                }
                else if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 7;
                }
                else if (this.world.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 10;
                }

                if (i > 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, 0));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }


    //てき（プレイヤーの登録っぽい
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
    }


    //敵（プレイヤー）の登録解除
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
    }


    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }


    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }

}