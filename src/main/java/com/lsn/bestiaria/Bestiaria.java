package com.lsn.bestiaria;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import com.lsn.bestiaria.init.ModEntityTypes;
import com.lsn.bestiaria.init.SoundInit;

@Mod("bestiaria")
@Mod.EventBusSubscriber(modid = Bestiaria.MOD_ID, bus = Bus.MOD)
public class Bestiaria
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "bestiaria";
    public static Bestiaria instance;

    public Bestiaria() 
    {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
    	
		SoundInit.SOUNDS.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {

    }
}
