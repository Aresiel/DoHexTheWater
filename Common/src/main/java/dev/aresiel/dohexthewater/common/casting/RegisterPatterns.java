package dev.aresiel.dohexthewater.common.casting;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import dev.aresiel.dohexthewater.api.DoHexTheWaterAPI;
import dev.aresiel.dohexthewater.common.casting.actions.*;

public class RegisterPatterns {
    public static void registerPatterns() {
        try {

            PatternRegistry.mapPattern(HexPattern.fromAngles("aqqwqqae", HexDir.NORTH_WEST),
                    DoHexTheWaterAPI.modLoc("normalize_vector"),
                    OpNormalizeVector.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("eweeewe", HexDir.NORTH_EAST),
                    DoHexTheWaterAPI.modLoc("vector_to_list"),
                    OpVectorToList.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("qwqqqwq", HexDir.NORTH_WEST),
                    DoHexTheWaterAPI.modLoc("list_to_vector"),
                    OpListToVector.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("aqqqqwqqwqwqwqwqw", HexDir.SOUTH_WEST),
                    DoHexTheWaterAPI.modLoc("theovore"),
                    OpTheovore.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("dawdwaddwwwaadwawda", HexDir.NORTH_EAST),
                    DoHexTheWaterAPI.modLoc("range"),
                    OpRange.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("waawwddaadd", HexDir.NORTH_EAST),
                    DoHexTheWaterAPI.modLoc("positivize_vector"),
                    OpPositiveVector.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("aqdeedqwawaad", HexDir.SOUTH_WEST),
                    DoHexTheWaterAPI.modLoc("halt_if_zero"),
                    OpHaltIfZero.INSTANCE);
            PatternRegistry.mapPattern(HexPattern.fromAngles("qeee", HexDir.NORTH_WEST),
                    DoHexTheWaterAPI.modLoc("rand_int"),
                    OpRandInt.INSTANCE);
        } catch (PatternRegistry.RegisterPatternException e) {
            e.printStackTrace();
        }
    }
}
