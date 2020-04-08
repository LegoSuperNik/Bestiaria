package com.lsn.bestiaria.util;

import com.lsn.bestiaria.Bestiaria;
import com.lsn.bestiaria.client.entity.render.RatonerRender;
import com.lsn.bestiaria.client.entity.render.monsterrender;
import com.lsn.bestiaria.init.ModEntityTypes;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Bestiaria.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RATONER.get(), RatonerRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MONSTERS.get(), monsterrender::new);
	}
}