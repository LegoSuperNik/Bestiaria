package com.lsn.bestiaria.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.lsn.bestiaria.init.SoundInit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class Ratoner extends MonsterEntity
{
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
	    this.applyEntityAI();
	    this.goalSelector.addGoal(2, new RatonerAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(0, new SwimGoal(this));
	}

	protected void applyEntityAI() {
	      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
	} 

	@Override
	protected void registerAttributes () {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
		   }  
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundInit.AMBIENT.get();
	}
    @Override
    public ILivingEntityData onInitialSpawn(
      final IWorld worldIn, final DifficultyInstance difficultyIn, final SpawnReason reason, @Nullable final ILivingEntityData spawnDataIn, @Nullable final CompoundNBT dataTag)
    {
        Ratoner.setEquipment(this);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
    public static void setEquipment(Ratoner mob)
    {
        mob.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_AXE));
    }
	public Ratoner(final EntityType<? extends MonsterEntity> type, final World world)
    {
        super(type, world);
        this.setInvulnerable(true);
        Ratoner.setEquipment(this);
    }
    @Override
    public boolean canPickUpLoot()
    {
        return true;
    }
    public boolean checkCanDropLoot()
    {
        return canDropLoot();
    }
    @Nonnull
    public IItemHandler getItemHandlerRatoner() 
    {
		return getInventoryRatoner();
	}

	private IItemHandler getInventoryRatoner() {
		return null;
	}
}