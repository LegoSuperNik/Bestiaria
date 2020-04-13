package com.lsn.bestiaria.entities;

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

public class Monsters extends MonsterEntity
{
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
	    this.applyEntityAI();
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
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
		   }  
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundInit.AMBIENT.get();
	}
	public Monsters(final EntityType<? extends MonsterEntity> type, final World world)
    {
        super(type, world);
        this.setInvulnerable(true);
    }
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
	      this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_AXE));
	   }

	   protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {
	   }

	   @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      ILivingEntityData ilivingentitydata = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	      return ilivingentitydata;
	   }
	   public static void setEquipment(Ratoner mob)
	    {
	        mob.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_AXE));
	    }

}