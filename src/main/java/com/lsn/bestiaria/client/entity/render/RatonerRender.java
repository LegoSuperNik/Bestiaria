package com.lsn.bestiaria.client.entity.render;

import com.lsn.bestiaria.Bestiaria;
import com.lsn.bestiaria.client.entity.model.RatonerModel;
import com.lsn.bestiaria.entities.Ratoner;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RatonerRender extends MobRenderer<Ratoner, RatonerModel<Ratoner>>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Bestiaria.MOD_ID, "textures/entity/ratoner.png");
	
	public RatonerRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new RatonerModel<Ratoner>(), 0.5f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(Ratoner entity) {
		return TEXTURE;
	}
}
