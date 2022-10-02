package dev.aresiel.dohexthewater.fabric

import dev.aresiel.dohexthewater.api.DoHexTheWaterAPI
import dev.aresiel.dohexthewater.common.casting.RegisterPatterns
import dev.aresiel.dohexthewater.fabric.network.FabricPacketHandler
import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import java.util.function.BiConsumer

object FabricDoHexTheWaterInitializer : ModInitializer {
    override fun onInitialize() {
        DoHexTheWaterAPI.LOGGER.info("Greetings, great world of Fabric! Salutations!")

        FabricPacketHandler.initServerBound()

        initListeners()

        initRegistries()

        RegisterPatterns.registerPatterns()
    }

    private fun initListeners() {
    }

    private fun initRegistries() {
    }


    private fun <T> bind(registry: Registry<in T>): BiConsumer<T, ResourceLocation> =
        BiConsumer<T, ResourceLocation> { t, id -> Registry.register(registry, id, t) }
}