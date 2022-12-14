package dev.aresiel.dohexthewater.fabric.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import org.apache.logging.log4j.util.TriConsumer
import java.util.function.Consumer
import java.util.function.Function

object FabricPacketHandler {
	/**
	 * For registering packets that are client -> server (called on the server)
	 */
	fun initServerBound() {

	}

	/**
	 * For registering packets that are server -> client (called on the client)
	 */
	fun initClientBound() {
	}

	private fun <T> makeServerBoundHandler(decoder: Function<FriendlyByteBuf, T>, handle: TriConsumer<T, MinecraftServer, ServerPlayer>):
					ServerPlayNetworking.PlayChannelHandler {
		return ServerPlayNetworking.PlayChannelHandler {
				server: MinecraftServer,
				player: ServerPlayer,
				_: ServerGamePacketListenerImpl,
				buf: FriendlyByteBuf,
				_: PacketSender ->
			handle.accept(decoder.apply(buf), server, player)
		}
	}

	private fun <T> makeClientBoundHandler(decoder: Function<FriendlyByteBuf, T>, handler: Consumer<T>): ClientPlayNetworking.PlayChannelHandler {
		return ClientPlayNetworking.PlayChannelHandler { _: Minecraft, _: ClientPacketListener, buf: FriendlyByteBuf, _: PacketSender ->
			handler.accept(decoder.apply(buf))
		}
	}
}