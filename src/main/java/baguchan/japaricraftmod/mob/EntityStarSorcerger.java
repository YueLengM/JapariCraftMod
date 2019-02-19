package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.handler.*;
import baguchan.japaricraftmod.mob.projectile.*;
import com.google.common.base.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EntityStarSorcerger extends EntitySpellcasterIllager {
    private EntitySheep wololoTarget;

    public EntityStarSorcerger(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 16;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityStarSorcerger.AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 1.0D));
        this.tasks.addTask(4, new EntityStarSorcerger.AISummonSpell());
        this.tasks.addTask(5, new EntityStarSorcerger.AIHealSpell());
        this.tasks.addTask(6, new EntityStarSorcerger.AIAttackSpell());
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityStarSorcerger.class));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JapariTreasure.starsorcerger;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        super.onUpdate();
    }

    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn) {
        if (entityIn == null) {
            return false;
        } else if (entityIn == this) {
            return true;
        } else if (super.isOnSameTeam(entityIn)) {
            return true;
        } else if (entityIn instanceof EntityVex) {
            return this.isOnSameTeam(((EntityVex) entityIn).getOwner());
        } else if (entityIn instanceof EntityCeruleanEye) {
            return this.isOnSameTeam(((EntityCeruleanEye) entityIn).getOwner());
        } else if (entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getCreatureAttribute() == EnumCreatureAttribute.ILLAGER) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_EVOCATION_ILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOCATION_ILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_EVOCATION_ILLAGER_HURT;
    }

    protected SoundEvent getSpellSound() {
        return SoundEvents.EVOCATION_ILLAGER_CAST_SPELL;
    }

    class AIAttackSpell extends EntitySpellcasterIllager.AIUseSpell {
        private int attackStep;
        private int attackTime;

        private AIAttackSpell() {
            super();
        }

        protected int getCastingTime() {
            return 40;
        }

        protected int getCastingInterval() {
            return 100;
        }

        protected void castSpell() {
            EntityLivingBase entitylivingbase = EntityStarSorcerger.this.getAttackTarget();


            for (int i = 0; i < 3; ++i) {
                Vec3d vec3d = EntityStarSorcerger.this.getLook(1.0F);

                double d1 = entitylivingbase.posX - (EntityStarSorcerger.this.posX + vec3d.x * 0.6D * i);
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (EntityStarSorcerger.this.posY + (double) (EntityStarSorcerger.this.height / 2.0F));
                double d3 = entitylivingbase.posZ - (EntityStarSorcerger.this.posZ + vec3d.z * 0.6D * i);
                EntityDarkSandStarball entityDarkSandStarball = new EntityDarkSandStarball(EntityStarSorcerger.this.world, EntityStarSorcerger.this, d1, d2, d3);

                entityDarkSandStarball.posX = EntityStarSorcerger.this.posX + vec3d.x * 1.2D * i;
                entityDarkSandStarball.posY = EntityStarSorcerger.this.posY + (double) (EntityStarSorcerger.this.height / 2.0F) + 0.5D;
                entityDarkSandStarball.posZ = EntityStarSorcerger.this.posZ + vec3d.z * 1.2D * i;
                EntityStarSorcerger.this.world.spawnEntity(entityDarkSandStarball);
                EntityStarSorcerger.this.playSound(SoundEvents.ENTITY_WITHER_SHOOT, 1.2F, EntityStarSorcerger.this.getSoundPitch());
            }

        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_ATTACK;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType() {
            return EntitySpellcasterIllager.SpellType.FANGS;
        }
    }

    class AICastingSpell extends EntitySpellcasterIllager.AICastingApell {
        private AICastingSpell() {
            super();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
            if (EntityStarSorcerger.this.getAttackTarget() != null) {
                EntityStarSorcerger.this.getLookHelper().setLookPositionWithEntity(EntityStarSorcerger.this.getAttackTarget(), (float) EntityStarSorcerger.this.getHorizontalFaceSpeed(), (float) EntityStarSorcerger.this.getVerticalFaceSpeed());
            }
        }
    }

    class AISummonSpell extends EntitySpellcasterIllager.AIUseSpell {
        private AISummonSpell() {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (!super.shouldExecute()) {
                return false;
            } else {
                int i = EntityStarSorcerger.this.world.getEntitiesWithinAABB(EntityCeruleanEye.class, EntityStarSorcerger.this.getEntityBoundingBox().grow(20.0D)).size();
                return EntityStarSorcerger.this.rand.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime() {
            return 100;
        }

        protected int getCastingInterval() {
            return 440;
        }

        protected void castSpell() {
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos = (new BlockPos(EntityStarSorcerger.this)).add(-2 + EntityStarSorcerger.this.rand.nextInt(5), 1, -2 + EntityStarSorcerger.this.rand.nextInt(5));
                EntityCeruleanEye entitycerulean = new EntityCeruleanEye(EntityStarSorcerger.this.world);
                entitycerulean.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
                entitycerulean.onInitialSpawn(EntityStarSorcerger.this.world.getDifficultyForLocation(blockpos), null);
                entitycerulean.setOwner(EntityStarSorcerger.this);
                EntityStarSorcerger.this.world.spawnEntity(entitycerulean);
            }
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_SUMMON;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType() {
            return EntitySpellcasterIllager.SpellType.SUMMON_VEX;
        }
    }

    public class AIHealSpell extends EntitySpellcasterIllager.AIUseSpell {
        final Predicate<EntitySheep> wololoSelector = new Predicate<EntitySheep>() {
            public boolean apply(EntitySheep p_apply_1_) {
                return p_apply_1_.getFleeceColor() == EnumDyeColor.BLUE;
            }
        };

        public AIHealSpell() {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (EntityStarSorcerger.this.isSpellcasting()) {
                return false;
            } else if (EntityStarSorcerger.this.ticksExisted < this.spellCooldown) {
                return false;
            } else {
                if (EntityStarSorcerger.this.getHealth() < EntityStarSorcerger.this.getMaxHealth() / 1.3F) {
                    return true;
                } else {
                    return true;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.spellWarmup > 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
        }

        protected void castSpell() {
            EntityStarSorcerger.this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 1));
            EntityStarSorcerger.this.heal(3.0F);
        }


        protected int getCastingTime() {
            return 60;
        }

        protected int getCastingInterval() {
            return 200;
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType() {
            return EntitySpellcasterIllager.SpellType.WOLOLO;
        }
    }

}