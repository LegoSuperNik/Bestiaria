package com.lsn.bestiaria.client.entity.render;

import com.lsn.bestiaria.client.entity.model.monster;
import com.lsn.bestiaria.entities.Monsters;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class monsterrender extends MobRenderer<Monsters, monster<Monsters>>
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
	
	public monsterrender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new monster<Monsters>(), 0.5f);
		this.addLayer(new HeldItemLayer_LSN<>(this));
	}
	
	@Override
	public ResourceLocation getEntityTexture(Monsters entity) {
		return TEXTURE;
	}
}