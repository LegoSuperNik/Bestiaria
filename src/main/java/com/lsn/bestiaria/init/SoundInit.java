package com.lsn.bestiaria.init;

import com.lsn.bestiaria.Bestiaria;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS,
			Bestiaria.MOD_ID);

	public static final RegistryObject<SoundEvent> AMBIENT = SOUNDS.register("entity.example_entity.ambient",
			() -> new SoundEvent(new ResourceLocation(Bestiaria.MOD_ID, "entity.example_entity.ambient")));
}
