package dev.aresiel.dohexthewater.common.casting;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import dev.aresiel.dohexthewater.api.DoHexTheWaterAPI;
import dev.aresiel.dohexthewater.common.casting.actions.OpListToVector;
import dev.aresiel.dohexthewater.common.casting.actions.OpNormalizeVector;
import dev.aresiel.dohexthewater.common.casting.actions.OpTheovore;
import dev.aresiel.dohexthewater.common.casting.actions.OpVectorToList;

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
            PatternRegistry.mapPattern(HexPattern.fromAngles(/*"qqedwewwwwewdeqqeaaqawewqwawwwwwwwawqwaqddadadadaddqewa"*/ "dwd", HexDir.NORTH_WEST),
                    DoHexTheWaterAPI.modLoc("theovore"),
                    OpTheovore.INSTANCE, true);
        } catch (PatternRegistry.RegisterPatternException e) {
            e.printStackTrace();
        }
    }
}
