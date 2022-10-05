package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import com.mojang.blaze3d.platform.ScreenManager.clamp
import net.minecraft.world.phys.Vec2
import net.minecraft.world.phys.Vec3
import java.lang.Integer.min
import kotlin.math.roundToInt
import kotlin.math.sqrt

object OpTheovore : ConstManaOperator {
    override val argc = 3

    override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
        val centrePosition = args.getChecked<Vec3>(0, argc)
        val plane = args.getChecked<Vec3>(1, argc)
        val index = args.getChecked<Double>(2, argc).roundToInt()



        ctx.assertVecInRange(centrePosition)

        if (plane != Vec3(0.0, 0.0, 1.0) && plane != Vec3(0.0, 1.0, 0.0) && plane != Vec3(1.0, 0.0, 0.0))
            throw MishapInvalidIota(args[1], 1, "dohexthewater.mishap.not_plane_vector".asTranslatedComponent())

        if (index < 0)
            throw MishapInvalidIota(args[1], 0, "dohexthewater.mishap.invalid_index".asTranslatedComponent())

        val worldPos = centrePosition.add(Vec2toVec3(pointOnSpiral(index), plane))
        return spellListOf(worldPos)
    }

    private fun pointOnSpiral(index: Int): Vec2 { // Credits to ForgottenHorror#8370 (571750094953775104) from the Petrak@'s Mods Discord for the algorithm
        val ring = ((sqrt(index.toDouble()))/2).roundToInt()
        val sideLength = 2*ring
        val offset = index - (2*ring-1)*(2*ring-1) + 1
        val startX = ring
        val startY = 1 - ring
        val offsetX = clamp(offset - 3*sideLength, 0, sideLength) - clamp(offset - sideLength, 0, sideLength)
        val offsetY = min(offset - 1, sideLength - 1) - clamp(offset - 2*sideLength, 0, sideLength)

        return Vec2((startX + offsetX).toFloat(), (startY + offsetY).toFloat())
    }

    private fun Vec2toVec3(position: Vec2, plane: Vec3): Vec3 {
        return if(plane.x == 1.0)
            Vec3(0.0, position.x.toDouble(), position.y.toDouble())
        else if (plane.y == 1.0)
            Vec3(position.x.toDouble(), 0.0, position.y.toDouble())
        else
            Vec3(position.x.toDouble(), position.y.toDouble(), 0.0)
    }
}
