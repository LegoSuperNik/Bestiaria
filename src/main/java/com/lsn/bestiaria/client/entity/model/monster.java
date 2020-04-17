package com.lsn.bestiaria.client.entity.model;

import com.lsn.bestiaria.entities.Monsters;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class monster<T extends Monsters> extends BM<T> {
   public monster() {
      this(0.0F, false);
   }

   public monster(float modelSize, boolean p_i46303_2_) {
      super(modelSize);
      if (!p_i46303_2_) {
         this.bRArm = new ModelRenderer(this, 40, 16);
         this.bRArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
         this.bRArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
         this.bLArm = new ModelRenderer(this, 40, 16);
         this.bLArm.mirror = true;
         this.bLArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
         this.bLArm.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedRightLeg = new ModelRenderer(this, 0, 16);
         this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
         this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
         this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
         this.bipedLeftLeg.mirror = true;
         this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
         this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
      }

   }

   public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
      this.rightArmPose = BM.ArmPose.EMPTY;
      this.leftArmPose = BM.ArmPose.EMPTY;
      ItemStack itemstack = entityIn.getHeldItem(Hand.MAIN_HAND);
      if (itemstack.getItem() instanceof net.minecraft.item.Item && entityIn.isAggressive()) {
         if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
            this.rightArmPose = BM.ArmPose.ITEM;
         } else {
            this.leftArmPose = BM.ArmPose.ITEM;
         }
      }

      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
   }

   /**
    * Sets this entity's model rotation angles
    */
   public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      ItemStack itemstack = entityIn.getHeldItemMainhand();
      if (entityIn.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof net.minecraft.item.BowItem))) {
         float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
         float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
         this.bRArm.rotateAngleZ = 0.0F;
         this.bLArm.rotateAngleZ = 0.0F;
         this.bRArm.rotateAngleY = -(0.1F - f * 0.6F);
         this.bLArm.rotateAngleY = 0.1F - f * 0.6F;
         this.bRArm.rotateAngleX = (-(float)Math.PI / 2F);
         this.bLArm.rotateAngleX = (-(float)Math.PI / 2F);
         this.bRArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
         this.bLArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
         this.bRArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
         this.bLArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
         this.bRArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
         this.bLArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      }

   }

   public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
      float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
      ModelRenderer modelrenderer = this.getArmForSide(sideIn);
      modelrenderer.rotationPointX += f;
      modelrenderer.translateRotate(matrixStackIn);
      modelrenderer.rotationPointX -= f;
   }
}