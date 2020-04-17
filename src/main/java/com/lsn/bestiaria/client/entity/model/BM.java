package com.lsn.bestiaria.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.function.Function;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BM<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {
   public ModelRenderer bipedHead;
   /** The Biped's Headgear. Used for the outer layer of player skins. */
   public ModelRenderer bipedHeadwear;
   public ModelRenderer bipedBody;
   /** The Biped's Right Arm */
   public ModelRenderer bRArm;
   /** The Biped's Left Arm */
   public ModelRenderer bLArm;
   /** The Biped's Right Leg */
   public ModelRenderer bipedRightLeg;
   /** The Biped's Left Leg */
   public ModelRenderer bipedLeftLeg;
   public BM.ArmPose leftArmPose = BM.ArmPose.EMPTY;
   public BM.ArmPose rightArmPose = BM.ArmPose.EMPTY;
   public boolean isSneak;
   public float swimAnimation;
   private float remainingItemUseTime;

   public BM(float modelSize) {
      this(RenderType::getEntityCutoutNoCull, modelSize, 0.0F, 64, 32);
   }

   protected BM(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
      this(RenderType::getEntityCutoutNoCull, modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
   }

   public BM(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
      super(renderTypeIn, true, 16.0F, 0.0F, 2.0F, 2.0F, 24.0F);
      this.textureWidth = textureWidthIn;
      this.textureHeight = textureHeightIn;
      this.bipedHead = new ModelRenderer(this, 0, 0);
      this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn);
      this.bipedHead.setRotationPoint(0.0F, 0.0F + yOffsetIn, 0.0F);
      this.bipedHeadwear = new ModelRenderer(this, 32, 0);
      this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn + 0.5F);
      this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + yOffsetIn, 0.0F);
      this.bipedBody = new ModelRenderer(this, 16, 16);
      this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, modelSizeIn);
      this.bipedBody.setRotationPoint(0.0F, 0.0F + yOffsetIn, 0.0F);
      this.bRArm = new ModelRenderer(this, 40, 16);
      this.bRArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
      this.bRArm.setRotationPoint(-5.0F, 2.0F + yOffsetIn, 0.0F);
      this.bLArm = new ModelRenderer(this, 40, 16);
      this.bLArm.mirror = true;
      this.bLArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
      this.bLArm.setRotationPoint(5.0F, 2.0F + yOffsetIn, 0.0F);
      this.bipedRightLeg = new ModelRenderer(this, 0, 16);
      this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
      this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + yOffsetIn, 0.0F);
      this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
      this.bipedLeftLeg.mirror = true;
      this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
      this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + yOffsetIn, 0.0F);
   }

   protected Iterable<ModelRenderer> getHeadParts() {
      return ImmutableList.of(this.bipedHead);
   }

   protected Iterable<ModelRenderer> getBodyParts() {
      return ImmutableList.of(this.bipedBody, this.bRArm, this.bLArm, this.bipedRightLeg, this.bipedLeftLeg, this.bipedHeadwear);
   }

   public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
      this.swimAnimation = entityIn.getSwimAnimation(partialTick);
      this.remainingItemUseTime = (float)entityIn.getItemInUseMaxCount();
      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
   }

   /**
    * Sets this entity's model rotation angles
    */
   @SuppressWarnings("incomplete-switch")
public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      boolean flag = entityIn.getTicksElytraFlying() > 4;
      boolean flag1 = entityIn.isActualySwimming();
      this.bipedHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
      if (flag) {
         this.bipedHead.rotateAngleX = (-(float)Math.PI / 4F);
      } else if (this.swimAnimation > 0.0F) {
         if (flag1) {
            this.bipedHead.rotateAngleX = this.rotLerpRad(this.bipedHead.rotateAngleX, (-(float)Math.PI / 4F), this.swimAnimation);
         } else {
            this.bipedHead.rotateAngleX = this.rotLerpRad(this.bipedHead.rotateAngleX, headPitch * ((float)Math.PI / 180F), this.swimAnimation);
         }
      } else {
         this.bipedHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
      }

      this.bipedBody.rotateAngleY = 0.0F;
      this.bRArm.rotationPointZ = 0.0F;
      this.bRArm.rotationPointX = -5.0F;
      this.bLArm.rotationPointZ = 0.0F;
      this.bLArm.rotationPointX = 5.0F;
      float f = 1.0F;
      if (flag) {
         f = (float)entityIn.getMotion().lengthSquared();
         f = f / 0.2F;
         f = f * f * f;
      }

      if (f < 1.0F) {
         f = 1.0F;
      }

      this.bRArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
      this.bLArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
      this.bRArm.rotateAngleZ = 0.0F;
      this.bLArm.rotateAngleZ = 0.0F;
      this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
      this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
      this.bipedRightLeg.rotateAngleY = 0.0F;
      this.bipedLeftLeg.rotateAngleY = 0.0F;
      this.bipedRightLeg.rotateAngleZ = 0.0F;
      this.bipedLeftLeg.rotateAngleZ = 0.0F;
      if (this.isSitting) {
         this.bRArm.rotateAngleX += (-(float)Math.PI / 5F);
         this.bLArm.rotateAngleX += (-(float)Math.PI / 5F);
         this.bipedRightLeg.rotateAngleX = -1.4137167F;
         this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
         this.bipedRightLeg.rotateAngleZ = 0.07853982F;
         this.bipedLeftLeg.rotateAngleX = -1.4137167F;
         this.bipedLeftLeg.rotateAngleY = (-(float)Math.PI / 10F);
         this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
      }

      this.bRArm.rotateAngleY = 0.0F;
      this.bRArm.rotateAngleZ = 0.0F;
      switch(this.leftArmPose) {
      case EMPTY:
         this.bLArm.rotateAngleY = 0.0F;
         break;
      case BLOCK:
         this.bLArm.rotateAngleX = this.bLArm.rotateAngleX * 0.5F - 0.9424779F;
         this.bLArm.rotateAngleY = ((float)Math.PI / 6F);
         break;
      case ITEM:
         this.bLArm.rotateAngleX = this.bLArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
         this.bLArm.rotateAngleY = 0.0F;
      }

      switch(this.rightArmPose) {
      case EMPTY:
         this.bRArm.rotateAngleY = 0.0F;
         break;
      case BLOCK:
         this.bRArm.rotateAngleX = this.bRArm.rotateAngleX * 0.5F - 0.9424779F;
         this.bRArm.rotateAngleY = (-(float)Math.PI / 6F);
         break;
      case ITEM:
         this.bRArm.rotateAngleX = this.bRArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
         this.bRArm.rotateAngleY = 0.0F;
         break;
      case THROW_SPEAR:
         this.bRArm.rotateAngleX = this.bRArm.rotateAngleX * 0.5F - (float)Math.PI;
         this.bRArm.rotateAngleY = 0.0F;
      }

      if (this.leftArmPose == BM.ArmPose.THROW_SPEAR && this.rightArmPose != BM.ArmPose.BLOCK && this.rightArmPose != BM.ArmPose.THROW_SPEAR && this.rightArmPose != BM.ArmPose.BOW_AND_ARROW) {
         this.bLArm.rotateAngleX = this.bLArm.rotateAngleX * 0.5F - (float)Math.PI;
         this.bLArm.rotateAngleY = 0.0F;
      }

      if (this.swingProgress > 0.0F) {
         HandSide handside = this.getMainHand(entityIn);
         ModelRenderer modelrenderer = this.getArmForSide(handside);
         float f1 = this.swingProgress;
         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;
         if (handside == HandSide.LEFT) {
            this.bipedBody.rotateAngleY *= -1.0F;
         }

         this.bRArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
         this.bRArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
         this.bLArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
         this.bLArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
         this.bRArm.rotateAngleY += this.bipedBody.rotateAngleY;
         this.bLArm.rotateAngleY += this.bipedBody.rotateAngleY;
         this.bLArm.rotateAngleX += this.bipedBody.rotateAngleY;
         f1 = 1.0F - this.swingProgress;
         f1 = f1 * f1;
         f1 = f1 * f1;
         f1 = 1.0F - f1;
         float f2 = MathHelper.sin(f1 * (float)Math.PI);
         float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
         modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
         modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
         modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
      }

      if (this.isSneak) {
         this.bipedBody.rotateAngleX = 0.5F;
         this.bRArm.rotateAngleX += 0.4F;
         this.bLArm.rotateAngleX += 0.4F;
         this.bipedRightLeg.rotationPointZ = 4.0F;
         this.bipedLeftLeg.rotationPointZ = 4.0F;
         this.bipedRightLeg.rotationPointY = 12.2F;
         this.bipedLeftLeg.rotationPointY = 12.2F;
         this.bipedHead.rotationPointY = 4.2F;
         this.bipedBody.rotationPointY = 3.2F;
         this.bLArm.rotationPointY = 5.2F;
         this.bRArm.rotationPointY = 5.2F;
      } else {
         this.bipedBody.rotateAngleX = 0.0F;
         this.bipedRightLeg.rotationPointZ = 0.1F;
         this.bipedLeftLeg.rotationPointZ = 0.1F;
         this.bipedRightLeg.rotationPointY = 12.0F;
         this.bipedLeftLeg.rotationPointY = 12.0F;
         this.bipedHead.rotationPointY = 0.0F;
         this.bipedBody.rotationPointY = 0.0F;
         this.bLArm.rotationPointY = 2.0F;
         this.bRArm.rotationPointY = 2.0F;
      }

      this.bRArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.bLArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.bRArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      this.bLArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      if (this.rightArmPose == BM.ArmPose.BOW_AND_ARROW) {
         this.bRArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
         this.bLArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
         this.bRArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
         this.bLArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
      } else if (this.leftArmPose == BM.ArmPose.BOW_AND_ARROW && this.rightArmPose != BM.ArmPose.THROW_SPEAR && this.rightArmPose != BM.ArmPose.BLOCK) {
         this.bRArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
         this.bLArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
         this.bRArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
         this.bLArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
      }

      float f4 = (float)CrossbowItem.getChargeTime(entityIn.getActiveItemStack());
      if (this.rightArmPose == BM.ArmPose.CROSSBOW_CHARGE) {
         this.bRArm.rotateAngleY = -0.8F;
         this.bRArm.rotateAngleX = -0.97079635F;
         this.bLArm.rotateAngleX = -0.97079635F;
         float f5 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
         this.bLArm.rotateAngleY = MathHelper.lerp(f5 / f4, 0.4F, 0.85F);
         this.bLArm.rotateAngleX = MathHelper.lerp(f5 / f4, this.bLArm.rotateAngleX, (-(float)Math.PI / 2F));
      } else if (this.leftArmPose == BM.ArmPose.CROSSBOW_CHARGE) {
         this.bLArm.rotateAngleY = 0.8F;
         this.bRArm.rotateAngleX = -0.97079635F;
         this.bLArm.rotateAngleX = -0.97079635F;
         float f6 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
         this.bRArm.rotateAngleY = MathHelper.lerp(f6 / f4, -0.4F, -0.85F);
         this.bRArm.rotateAngleX = MathHelper.lerp(f6 / f4, this.bRArm.rotateAngleX, (-(float)Math.PI / 2F));
      }

      if (this.rightArmPose == BM.ArmPose.CROSSBOW_HOLD && this.swingProgress <= 0.0F) {
         this.bRArm.rotateAngleY = -0.3F + this.bipedHead.rotateAngleY;
         this.bLArm.rotateAngleY = 0.6F + this.bipedHead.rotateAngleY;
         this.bRArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
         this.bLArm.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
      } else if (this.leftArmPose == BM.ArmPose.CROSSBOW_HOLD) {
         this.bRArm.rotateAngleY = -0.6F + this.bipedHead.rotateAngleY;
         this.bLArm.rotateAngleY = 0.3F + this.bipedHead.rotateAngleY;
         this.bRArm.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
         this.bLArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
      }

      if (this.swimAnimation > 0.0F) {
         float f7 = limbSwing % 26.0F;
         float f8 = this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
         if (f7 < 14.0F) {
            this.bLArm.rotateAngleX = this.rotLerpRad(this.bLArm.rotateAngleX, 0.0F, this.swimAnimation);
            this.bRArm.rotateAngleX = MathHelper.lerp(f8, this.bRArm.rotateAngleX, 0.0F);
            this.bLArm.rotateAngleY = this.rotLerpRad(this.bLArm.rotateAngleY, (float)Math.PI, this.swimAnimation);
            this.bRArm.rotateAngleY = MathHelper.lerp(f8, this.bRArm.rotateAngleY, (float)Math.PI);
            this.bLArm.rotateAngleZ = this.rotLerpRad(this.bLArm.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F), this.swimAnimation);
            this.bRArm.rotateAngleZ = MathHelper.lerp(f8, this.bRArm.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F));
         } else if (f7 >= 14.0F && f7 < 22.0F) {
            float f10 = (f7 - 14.0F) / 8.0F;
            this.bLArm.rotateAngleX = this.rotLerpRad(this.bLArm.rotateAngleX, ((float)Math.PI / 2F) * f10, this.swimAnimation);
            this.bRArm.rotateAngleX = MathHelper.lerp(f8, this.bRArm.rotateAngleX, ((float)Math.PI / 2F) * f10);
            this.bLArm.rotateAngleY = this.rotLerpRad(this.bLArm.rotateAngleY, (float)Math.PI, this.swimAnimation);
            this.bRArm.rotateAngleY = MathHelper.lerp(f8, this.bRArm.rotateAngleY, (float)Math.PI);
            this.bLArm.rotateAngleZ = this.rotLerpRad(this.bLArm.rotateAngleZ, 5.012389F - 1.8707964F * f10, this.swimAnimation);
            this.bRArm.rotateAngleZ = MathHelper.lerp(f8, this.bRArm.rotateAngleZ, 1.2707963F + 1.8707964F * f10);
         } else if (f7 >= 22.0F && f7 < 26.0F) {
            float f9 = (f7 - 22.0F) / 4.0F;
            this.bLArm.rotateAngleX = this.rotLerpRad(this.bLArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9, this.swimAnimation);
            this.bRArm.rotateAngleX = MathHelper.lerp(f8, this.bRArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9);
            this.bLArm.rotateAngleY = this.rotLerpRad(this.bLArm.rotateAngleY, (float)Math.PI, this.swimAnimation);
            this.bRArm.rotateAngleY = MathHelper.lerp(f8, this.bRArm.rotateAngleY, (float)Math.PI);
            this.bLArm.rotateAngleZ = this.rotLerpRad(this.bLArm.rotateAngleZ, (float)Math.PI, this.swimAnimation);
            this.bRArm.rotateAngleZ = MathHelper.lerp(f8, this.bRArm.rotateAngleZ, (float)Math.PI);
         }

         this.bipedLeftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedLeftLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
         this.bipedRightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedRightLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
      }

      this.bipedHeadwear.copyModelAngles(this.bipedHead);
   }

   protected float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
      float f = (maxAngleIn - angleIn) % ((float)Math.PI * 2F);
      if (f < -(float)Math.PI) {
         f += ((float)Math.PI * 2F);
      }

      if (f >= (float)Math.PI) {
         f -= ((float)Math.PI * 2F);
      }

      return angleIn + mulIn * f;
   }

   private float getArmAngleSq(float limbSwing) {
      return -65.0F * limbSwing + limbSwing * limbSwing;
   }

   public void setModelAttributes(BM<T> modelIn) {
      super.copyModelAttributesTo(modelIn);
      modelIn.leftArmPose = this.leftArmPose;
      modelIn.rightArmPose = this.rightArmPose;
      modelIn.isSneak = this.isSneak;
   }

   public void setVisible(boolean visible) {
      this.bipedHead.showModel = visible;
      this.bipedHeadwear.showModel = visible;
      this.bipedBody.showModel = visible;
      this.bRArm.showModel = visible;
      this.bLArm.showModel = visible;
      this.bipedRightLeg.showModel = visible;
      this.bipedLeftLeg.showModel = visible;
   }

   public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
      this.getArmForSide(sideIn).translateRotate(matrixStackIn);
   }

   protected ModelRenderer getArmForSide(HandSide side) {
      return side == HandSide.LEFT ? this.bLArm : this.bRArm;
   }

   public ModelRenderer getModelHead() {
      return this.bipedHead;
   }

   protected HandSide getMainHand(T entityIn) {
      HandSide handside = entityIn.getPrimaryHand();
      return entityIn.swingingHand == Hand.MAIN_HAND ? handside : handside.opposite();
   }

   @OnlyIn(Dist.CLIENT)
   public static enum ArmPose {
      EMPTY,
      ITEM,
      BLOCK,
      BOW_AND_ARROW,
      THROW_SPEAR,
      CROSSBOW_CHARGE,
      CROSSBOW_HOLD;
   }
}