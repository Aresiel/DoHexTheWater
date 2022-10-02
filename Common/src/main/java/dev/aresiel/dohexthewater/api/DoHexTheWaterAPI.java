package dev.aresiel.dohexthewater.api;

import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public interface DoHexTheWaterAPI
{
	String MOD_ID = "dohexthewater";
	Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	Supplier<DoHexTheWaterAPI> INSTANCE = Suppliers.memoize(() -> {
		try {
			return (DoHexTheWaterAPI) Class.forName("dev.aresiel.dohexthewater.common.impl.DoHexTheWaterAPIImpl")
								 .getDeclaredConstructor().newInstance();
		} catch (ReflectiveOperationException e) {
			LogManager.getLogger().warn("Unable to find DoHexTheWaterAPIImpl, using a dummy");
			return new DoHexTheWaterAPI() {
			};
		}
	});
	
	static DoHexTheWaterAPI instance() {
		return INSTANCE.get();
	}
	
	static ResourceLocation modLoc(String s) {
		return new ResourceLocation(MOD_ID, s);
	}
}
