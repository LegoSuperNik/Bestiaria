package com.lsn.bestiaria.entities;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class RatonerAttackGoal extends MeleeAttackGoal {
	 private final Ratoner rat;
		   private int raiseArmTicks;

		   public RatonerAttackGoal(Ratoner ratIn, double speedIn, boolean longMemoryIn) {
		      super(ratIn, speedIn, longMemoryIn);
		      this.rat = ratIn;
		   }

		   /**
		    * Execute a one shot task or start executing a continuous task
		    */
		   public void startExecuting() {
		      super.startExecuting();
		      this.raiseArmTicks = 0;
		   }

		   /**
		    * Reset the task's internal state. Called when this task is interrupted by another one
		    */
		   public void resetTask() {
		      super.resetTask();
		      this.rat.setAggroed(false);
		   }

		   /**
		    * Keep ticking a continuous task that has already been started
		    */
		   public void tick() {
		      super.tick();
		      ++this.raiseArmTicks;
		      if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
		         this.rat.setAggroed(true);
		      } else {
		         this.rat.setAggroed(false);
		      }

		   }
		}