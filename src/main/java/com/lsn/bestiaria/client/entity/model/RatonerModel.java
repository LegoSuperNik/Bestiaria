package com.lsn.bestiaria.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.lsn.bestiaria.entities.Ratoner;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.item.ItemStack;

@OnlyIn(Dist.CLIENT)
public class RatonerModel<T extends Ratoner> extends SegmentedModel<T> implements IHasArm, IHasHead{
	public RatonerModel.ArmPose leftArmPose = RatonerModel.ArmPose.EMPTY;
	public RatonerModel.ArmPose rightArmPose = RatonerModel.ArmPose.EMPTY;
	
	private final ModelRenderer Body;
	private final ModelRenderer bodymid;
	private final ModelRenderer Bodyup;
	private final ModelRenderer Head;
	private final ModelRenderer Mouthr;
	private final ModelRenderer MouthL;
	private final ModelRenderer Mouthdown;
	private final ModelRenderer Hair;
	private final ModelRenderer EarRight;
	private final ModelRenderer EarLeft;
	private final ModelRenderer Mouth;
	private final ModelRenderer mouthA;
	private final ModelRenderer Nose;
	private final ModelRenderer ArmLeft;
	private final ModelRenderer Arml;
	private final ModelRenderer handl;
	private final ModelRenderer ArmRight;
	private final ModelRenderer Armr;
	private final ModelRenderer handr;
	private final ModelRenderer RLeg;
	private final ModelRenderer legr;
	private final ModelRenderer legrdown;
	private final ModelRenderer bone2;
	private final ModelRenderer LLeg;
	private final ModelRenderer legl;
	private final ModelRenderer legldown;
	private final ModelRenderer bone;
	private final ModelRenderer Tale;
	private final ModelRenderer Tale2;
	private final ModelRenderer Tale3;
	private final ModelRenderer Tale4;
	private final ModelRenderer Tale5;

	public RatonerModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 14.0F, 0.0F);
		Body.setTextureOffset(9, 16).addBox(-3.0F, -0.5F, -2.0F, 6, 4, 4, 0.0F, false);
		
		bodymid = new ModelRenderer(this);
		bodymid.setRotationPoint(0.0F, -2.0F, 0.0F);
		Body.addChild(bodymid);
		bodymid.setTextureOffset(12, 8).addBox(-2.5F, -1.0F, -1.5F, 5, 3, 3, 0.0F, false);
		
		Bodyup = new ModelRenderer(this);
		Bodyup.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(Bodyup, 0.2618F, 0.0F, 0.0F);
		bodymid.addChild(Bodyup);
		Bodyup.setTextureOffset(9, 0).addBox(-3.0F, -2.4659F, -2.0412F, 6, 3, 4, 0.0F, false);
		
		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -1.9659F, -0.7412F);
		setRotationAngle(Head, -0.2618F, 0.0F, 0.0F);
		Bodyup.addChild(Head);
		Head.setTextureOffset(30, 0).addBox(-2.0F, -3.0F, -2.0F, 4, 3, 3, 0.0F, false);
		
		Mouthr = new ModelRenderer(this);
		Mouthr.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotationAngle(Mouthr, 0.3491F, -0.2618F, 0.0F);
		Head.addChild(Mouthr);
		Mouthr.setTextureOffset(56, 33).addBox(-1.4F, -0.8341F, -3.2588F, 1, 1, 3, 0.0F, false);
		
		MouthL = new ModelRenderer(this);
		MouthL.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotationAngle(MouthL, 0.3491F, 0.2618F, 0.0F);
		Head.addChild(MouthL);
		MouthL.setTextureOffset(47, 32).addBox(0.4F, -0.8341F, -3.2588F, 1, 1, 3, 0.0F, false);
		
		Mouthdown = new ModelRenderer(this);
		Mouthdown.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotationAngle(Mouthdown, 0.3491F, 0.0F, 0.0F);
		Head.addChild(Mouthdown);
		Mouthdown.setTextureOffset(58, 39).addBox(-0.5F, 0.1659F, -2.2588F, 1, 0, 2, 0.0F, false);
		
		Hair = new ModelRenderer(this);
		Hair.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Hair);
		Hair.setTextureOffset(56, 0).addBox(0.0F, -4.5F, -2.499F, 0, 4, 4, 0.0F, false);
		
		EarRight = new ModelRenderer(this);
		EarRight.setRotationPoint(-2.0F, -2.0F, 0.0F);
		setRotationAngle(EarRight, 0.0F, 0.7854F, 0.2618F);
		Head.addChild(EarRight);
		EarRight.setTextureOffset(46, 0).addBox(-3.0F, -2.0F, 0.0F, 3, 2, 0, 0.0F, false);
		
		EarLeft = new ModelRenderer(this);
		EarLeft.setRotationPoint(2.0F, -2.0F, 0.0F);
		setRotationAngle(EarLeft, 0.0F, -0.7854F, -0.2618F);
		Head.addChild(EarLeft);
		EarLeft.setTextureOffset(46, 2).addBox(0.0F, -2.0F, 0.0F, 3, 2, 0, 0.0F, false);
		
		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, -2.0F, -2.0F);
		setRotationAngle(Mouth, 0.0873F, 0.0F, 0.0F);
		Head.addChild(Mouth);
		Mouth.setTextureOffset(30, 7).addBox(-1.5F, 0.0F, -2.0F, 3, 2, 2, 0.0F, false);
		
		mouthA = new ModelRenderer(this);
		mouthA.setRotationPoint(1.0F, 1.0F, -1.0F);
		setRotationAngle(mouthA, 0.0873F, 0.0F, 0.0F);
		Mouth.addChild(mouthA);
		mouthA.setTextureOffset(30, 12).addBox(-2.0F, 0.0F, -2.0F, 2, 1, 1, 0.0F, false);
		
		Nose = new ModelRenderer(this);
		Nose.setRotationPoint(-1.0F, 0.0F, -2.0F);
		setRotationAngle(Nose, 0.1745F, 0.0F, 0.0F);
		mouthA.addChild(Nose);
		Nose.setTextureOffset(30, 15).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F, false);
		
		ArmLeft = new ModelRenderer(this);
		ArmLeft.setRotationPoint(3.0F, -0.9659F, -0.7412F);
		setRotationAngle(ArmLeft, -0.0873F, 0.0F, -0.3491F);
		Bodyup.addChild(ArmLeft);
		ArmLeft.setTextureOffset(0, 0).addBox(-0.0937F, -0.8506F, -0.9247F, 2, 6, 2, 0.0F, true);
		
		Arml = new ModelRenderer(this);
		Arml.setRotationPoint(0.9063F, 5.1494F, 1.0753F);
		setRotationAngle(Arml, 0.3491F, 0.0F, 0.1745F);
		ArmLeft.addChild(Arml);
		Arml.setTextureOffset(0, 8).addBox(-0.5F, -0.925F, -5.1318F, 1, 1, 5, 0.0F, false);
		
		handl = new ModelRenderer(this);
		handl.setRotationPoint(0.0937F, -0.1835F, -4.3341F);
		Arml.addChild(handl);
		handl.setTextureOffset(0, 16).addBox(-1.0937F, -1.3165F, -1.6659F, 2, 2, 2, 0.0F, true);
		
		ArmRight = new ModelRenderer(this);
		ArmRight.setRotationPoint(-3.0F, -0.9659F, -0.7412F);
		setRotationAngle(ArmRight, -0.0873F, 0.0F, 0.3491F);
		Bodyup.addChild(ArmRight);
		ArmRight.setTextureOffset(0, 0).addBox(-1.9063F, -0.8506F, -0.9247F, 2, 6, 2, 0.0F, false);
		
		Armr = new ModelRenderer(this);
		Armr.setRotationPoint(-0.9063F, 5.1494F, 1.0753F);
		setRotationAngle(Armr, 0.3491F, 0.0F, -0.1745F);
		ArmRight.addChild(Armr);
		Armr.setTextureOffset(0, 8).addBox(-0.5F, -0.925F, -5.1318F, 1, 1, 5, 0.0F, true);
		
		handr = new ModelRenderer(this);
		handr.setRotationPoint(-0.0937F, -0.1835F, -4.3341F);
		Armr.addChild(handr);
		handr.setTextureOffset(0, 16).addBox(-1.0937F, -1.3165F, -1.6659F, 2, 2, 2, 0.0F, false);
		
		RLeg = new ModelRenderer(this);
		RLeg.setRotationPoint(-2.0F, 5.0F, 0.0F);
		setRotationAngle(RLeg, 2.2689F, 3.1416F, 0.0F);
		bodymid.addChild(RLeg);
		RLeg.setTextureOffset(0, 57).addBox(-0.999F, -1.3963F, -4.5837F, 2, 2, 5, 0.0F, false);
		
		legr = new ModelRenderer(this);
		legr.setRotationPoint(0.0F, 0.234F, -4.3572F);
		setRotationAngle(legr, 3.1416F, 0.0F, 0.0F);
		RLeg.addChild(legr);
		legr.setTextureOffset(14, 51).addBox(-0.5F, -0.7324F, -0.3F, 1, 4, 2, 0.0F, false);
		
		legrdown = new ModelRenderer(this);
		legrdown.setRotationPoint(0.0F, 3.1902F, -0.6288F);
		setRotationAngle(legrdown, -0.3491F, 3.1416F, 0.0F);
		legr.addChild(legrdown);
		legrdown.setTextureOffset(21, 41).addBox(-0.501F, -1.1069F, -1.4715F, 1, 1, 3, 0.0F, false);
		
		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, -2.0F);
		setRotationAngle(bone2, 1.0472F, 0.0F, 0.0F);
		legrdown.addChild(bone2);
		bone2.setTextureOffset(0, 27).addBox(-1.0F, 0.0F, -1.314F, 2, 3, 1, 0.0F, true);

		LLeg = new ModelRenderer(this);
		LLeg.setRotationPoint(2.0F, 5.0F, 0.0F);
		setRotationAngle(LLeg, 2.2689F, 3.1416F, 0.0F);
		bodymid.addChild(LLeg);
		LLeg.setTextureOffset(0, 49).addBox(-0.999F, -1.3963F, -4.5837F, 2, 2, 5, 0.0F, false);
		
		legl = new ModelRenderer(this);
		legl.setRotationPoint(0.0F, 0.234F, -4.3572F);
		setRotationAngle(legl, 3.1416F, 0.0F, 0.0F);
		LLeg.addChild(legl);
		legl.setTextureOffset(14, 51).addBox(-0.5F, -0.7324F, -0.3F, 1, 4, 2, 0.0F, false);
		
		legldown = new ModelRenderer(this);
		legldown.setRotationPoint(0.0F, 3.1902F, -0.6288F);
		setRotationAngle(legldown, -0.3491F, 3.1416F, 0.0F);
		legl.addChild(legldown);
		legldown.setTextureOffset(21, 49).addBox(-0.501F, -1.1069F, -1.4715F, 1, 1, 3, 0.0F, false);
		
		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, -2.0F);
		setRotationAngle(bone, 1.0472F, 0.0F, 0.0F);
		legldown.addChild(bone);
		bone.setTextureOffset(0, 27).addBox(-1.0F, 0.0F, -1.314F, 2, 3, 1, 0.0F, false);
		
		Tale = new ModelRenderer(this);
		Tale.setRotationPoint(0.0F, 3.0F, 2.0F);
		setRotationAngle(Tale, -0.8727F, 0.0F, 0.0F);
		bodymid.addChild(Tale);
		Tale.setTextureOffset(52, 59).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3, 0.0F, false);
		
		Tale2 = new ModelRenderer(this);
		Tale2.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(Tale2, -0.4363F, 0.0F, 0.0F);
		Tale.addChild(Tale2);
		Tale2.setTextureOffset(56, 53).addBox(-1.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false);

		Tale3 = new ModelRenderer(this);
		Tale3.setRotationPoint(0.0F, 2.0F, 2.0F);
		setRotationAngle(Tale3, 0.2618F, 0.0F, 0.0F);
		Tale2.addChild(Tale3);
		Tale3.setTextureOffset(58, 48).addBox(-0.999F, -1.9924F, -0.1743F, 2, 2, 2, 0.0F, false);
		
		Tale4 = new ModelRenderer(this);
		Tale4.setRotationPoint(0.0F, -0.4924F, 1.8257F);
		setRotationAngle(Tale4, 0.3491F, 0.0F, 0.0F);
		Tale3.addChild(Tale4);
		Tale4.setTextureOffset(58, 44).addBox(-0.5F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false);
		
		Tale5 = new ModelRenderer(this);
		Tale5.setRotationPoint(0.0F, 0.0F, 2.0F);
		setRotationAngle(Tale5, 0.6109F, 0.0F, 0.0F);
		Tale4.addChild(Tale5);
		Tale5.setTextureOffset(43, 60).addBox(-0.499F, -1.0076F, 0.0F, 1, 1, 3, 0.0F, false);
	}

	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	      float f = 1.0F;
	      if (f < 1.0F) {
	         f = 1.0F;
	      }
 
	      this.LLeg.rotateAngleX = MathHelper.cos(limbSwing *  0.75F) * 0.5F * limbSwingAmount / f + 0.7F;
	      this.RLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 1.0F * limbSwingAmount / f + 0.7F;
	      this.LLeg.rotateAngleY = -0.2F;
	      this.RLeg.rotateAngleY = 0.2F;
	      this.LLeg.rotateAngleZ = 0.0F;
	      this.RLeg.rotateAngleZ = 0.0F;
	      this.legl.rotateAngleX = MathHelper.cos(limbSwing * 0.75F) * 0.5F * limbSwingAmount / f + 0.5F;
	      this.legr.rotateAngleX = MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.5F * limbSwingAmount / f + 0.5F;
	      this.legl.rotateAngleY = 0.0F;
	      this.legr.rotateAngleY = 0.0F;
	      this.legl.rotateAngleZ = 0.0F;
	      this.legr.rotateAngleZ = 0.0F;
	      this.legldown.rotateAngleX = MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.5F * limbSwingAmount / f;
	      this.legrdown.rotateAngleX = MathHelper.cos(limbSwing * 0.75F) * 0.5F * limbSwingAmount / f;
	      this.legldown.rotateAngleY = 0.0F;
	      this.legrdown.rotateAngleY = 0.0F;
	      this.legldown.rotateAngleZ = 0.0F;
	      this.legrdown.rotateAngleZ = 0.0F;
	      this.bone.rotateAngleX = MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.5F * limbSwingAmount / f + 3.5F;
	      this.bone2.rotateAngleX = MathHelper.cos(limbSwing * 0.75F) * 0.5F * limbSwingAmount / f + 3.5F;
	      this.bone.rotateAngleY = 0.0F;
	      this.bone2.rotateAngleY = 0.0F;
	      this.bone.rotateAngleZ = 0.0F;
	      this.bone2.rotateAngleZ = 0.0F;
	      
	      /**TEST ROTATION FOR HANDS
	      
	      this.handl.rotateAngleX = MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 1.4F * limbSwingAmount / f - 2.36F;
	      this.handr.rotateAngleX = MathHelper.cos(limbSwing * 0.75F) * 1.4F * limbSwingAmount / f - 2.36F;
	      this.handl.rotateAngleY = 0.0F;
	      this.handr.rotateAngleY = 0.0F;
	      this.handl.rotateAngleZ = 0.0F;
	      this.handr.rotateAngleZ = 0.0F;
	      */
	      this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	      this.Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

	      this.handr.rotateAngleY = 0.0F;
	      this.handl.rotateAngleZ = 0.0F;
	      switch(this.leftArmPose) {
	      case EMPTY:
	         this.handl.rotateAngleY = 0.0F;
	         break;
	      case BLOCK:
	         this.handl.rotateAngleX = this.handl.rotateAngleX * 0.5F - 0.9424779F;
	         this.handl.rotateAngleY = ((float)Math.PI / 6F);
	         break;
	      case ITEM:
	         this.handl.rotateAngleX = this.handl.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
	         this.handl.rotateAngleY = 0.0F;
		default:
			break;
	      }

	      switch(this.rightArmPose) {
	      case EMPTY:
	         this.handr.rotateAngleY = 0.0F;
	         break;
	      case BLOCK:
	         this.handr.rotateAngleX = this.handr.rotateAngleX * 0.5F - 0.9424779F;
	         this.handr.rotateAngleY = (-(float)Math.PI / 6F);
	         break;
	      case ITEM:
	         this.handr.rotateAngleX = this.handr.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
	         this.handr.rotateAngleY = 0.0F;
	         break;
	      case THROW_SPEAR:
	         this.handr.rotateAngleX = this.handr.rotateAngleX * 0.5F - (float)Math.PI;
	         this.handr.rotateAngleY = 0.0F;
		default:
			break;
	      }
	      if (this.leftArmPose == RatonerModel.ArmPose.THROW_SPEAR && this.rightArmPose != RatonerModel.ArmPose.BLOCK && this.rightArmPose != RatonerModel.ArmPose.THROW_SPEAR && this.rightArmPose != RatonerModel.ArmPose.BOW_AND_ARROW) {
	          this.handl.rotateAngleX = this.handl.rotateAngleX * 0.5F - (float)Math.PI;
	          this.handl.rotateAngleY = 0.0F;
	       }
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	 public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
	      this.rightArmPose = RatonerModel.ArmPose.EMPTY;
	      this.leftArmPose = RatonerModel.ArmPose.EMPTY;
	      ItemStack itemstack = ((LivingEntity) entityIn).getHeldItem(Hand.MAIN_HAND);
	      if (itemstack.getItem() instanceof net.minecraft.item.Item && entityIn.isAlive()) {
	         if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
	            this.rightArmPose = RatonerModel.ArmPose.ITEM;
	         } else {
	            this.leftArmPose = RatonerModel.ArmPose.ITEM;
	         }
	      }

	      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	   }

	public ModelRenderer getModelHead() {
		return this.Head;
	}

	public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
		 this.getArmForSide(sideIn).translateRotate(matrixStackIn);
		 float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
	      ModelRenderer modelrenderer = this.getArmForSide(sideIn);
	      modelrenderer.rotationPointX += f;
	      modelrenderer.translateRotate(matrixStackIn);
	      modelrenderer.rotationPointX -= f;
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

	@SuppressWarnings("unused")
	private float getArmAngleSq(float limbSwing) {
	      return -65.0F * limbSwing + limbSwing * limbSwing;
	   }
	   public void setModelAttributes(RatonerModel<T> modelIn) {
		      super.copyModelAttributesTo(modelIn);
		      modelIn.leftArmPose = this.leftArmPose;
		      modelIn.rightArmPose = this.rightArmPose;
		   }
	protected ModelRenderer getArmForSide(HandSide side) {
	      return side == HandSide.LEFT ? this.handl : this.handr;
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
	   public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.Arml, this.ArmLeft, this.Armr, this.ArmRight, this.Body, this.bodymid,
				this.Bodyup, this.bone, this.bone2, this.EarLeft, this.EarRight,
				this.Hair, this.handl, this.handr, this.legl, this.legldown,
				this.LLeg, this.Mouth, this.legr,this.legrdown, this.mouthA,
				this.Mouthdown, this.MouthL, this.Mouthr, this.Nose, this.RLeg, 
				this.Tale, this.Tale2, this.Tale3, this.Tale4, this.Tale5, this.Head);
	}
}