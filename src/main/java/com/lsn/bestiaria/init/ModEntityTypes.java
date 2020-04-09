package com.lsn.bestiaria.init;

import com.lsn.bestiaria.Bestiaria;
import com.lsn.bestiaria.entities.Monsters;
import com.lsn.bestiaria.entities.Ratoner;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			Bestiaria.MOD_ID);

	public static final RegistryObject<EntityType<Ratoner>> RATONER = ENTITY_TYPES
			.register("ratoner",
					() -> EntityType.Builder.<Ratoner>create(Ratoner::new, EntityClassification.CREATURE)
							.size(0.7f, 1.3f)
							.build(new ResourceLocation(Bestiaria.MOD_ID, "monster").toString()));
	public static final RegistryObject<EntityType<Monsters>> MONSTERS = ENTITY_TYPES
			.register("monster",
					() -> EntityType.Builder.<Monsters>create(Monsters::new, EntityClassification.CREATURE)
							.size(0.9f, 2.3f)
							.build(new ResourceLocation(Bestiaria.MOD_ID, "monster").toString()));
}