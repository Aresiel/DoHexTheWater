package dev.aresiel.dohexthewater.forge;

import dev.aresiel.dohexthewater.api.DoHexTheWaterAPI;
import dev.aresiel.dohexthewater.common.casting.RegisterPatterns;
import dev.aresiel.dohexthewater.forge.network.ForgePacketHandler;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thedarkcolour.kotlinforforge.KotlinModLoadingContext;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(DoHexTheWaterAPI.MOD_ID)
public class ForgeDoHexTheWaterInitializer {
	
	public ForgeDoHexTheWaterInitializer () {
		DoHexTheWaterAPI.LOGGER.info("Greetings, great world of Forge! Salutations!");
		initConfig();
		initRegistry();
		initListeners();
	}
	
	private static void initConfig () {
	
	}
	
	private static void initRegistry () {
	}
	
	private static void initListeners () {
		IEventBus modBus = getModEventBus();
		IEventBus evBus = MinecraftForge.EVENT_BUS;
		
		modBus.addListener((FMLCommonSetupEvent evt) ->
			 evt.enqueueWork(() -> {
				 //noinspection Convert2MethodRef
				 ForgePacketHandler.init();
			 }));
		
		modBus.addListener((FMLCommonSetupEvent evt) -> evt.enqueueWork(RegisterPatterns::registerPatterns));
	}
	
	// https://github.com/VazkiiMods/Botania/blob/1.18.x/Forge/src/main/java/vazkii/botania/forge/ForgeCommonInitializer.java
	private static <T extends IForgeRegistryEntry<T>> void bind (IForgeRegistry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
		getModEventBus().addGenericListener(registry.getRegistrySuperType(), (RegistryEvent.Register<T> event) -> {
			IForgeRegistry<T> forgeRegistry = event.getRegistry();
			source.accept((t, rl) -> {
				t.setRegistryName(rl);
				forgeRegistry.register(t);
			});
		});
	}
	
	// This version of bind is used for BuiltinRegistries.
	private static <T> void bind(Registry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
		source.accept((t, id) -> Registry.register(registry, id, t));
	}
	
	private static IEventBus getModEventBus () {
		return KotlinModLoadingContext.Companion.get().getKEventBus();
	}
}