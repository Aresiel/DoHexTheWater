package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.world.phys.Vec2
import net.minecraft.world.phys.Vec3
import kotlin.math.roundToInt

object OpTheovore : SpellOperator {
    override val argc = 4
    override val isGreat = true

    override fun execute(
        args: List<SpellDatum<*>>, ctx: CastingContext
    ): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        val patternList = args.getChecked<SpellList>(0, argc)
        val plane = args.getChecked<Vec3>(1, argc)
        val amountOfSteps = args.getChecked<Double>(2, argc).roundToInt()
        val position = args.getChecked<Vec3>(3, argc)

        ctx.assertVecInRange(position)

        if (plane != Vec3(0.0, 0.0, 1.0) && plane != Vec3(0.0, 1.0, 0.0) && plane != Vec3(1.0, 0.0, 0.0)) {
            throw MishapInvalidIota(args[1], 0, "dohexthewater.mishap.not_plane_vector".asTranslatedComponent())
        }


        return Triple(Spell(ctx, patternList, amountOfSteps, position, plane), 10 * amountOfSteps, listOf())
    }

    private data class Spell(
        val ctx: CastingContext, val patternList: SpellList, var stepsRemaining: Int, val center_position: Vec3, val plane: Vec3
    ) : RenderedSpell {
        override fun cast(ctx: CastingContext) {

            var position = Vec2(0f, 0f)
            val positions = mutableListOf<Vec2>(position)
            var direction = Vec2(1f, 0f)
            var currentStepsAmount = 1

            while (stepsRemaining > 0) { // Build the positions array
                for (step in 1..currentStepsAmount) { // Walk current_width steps once
                    position = position.add(direction) // Add direction to position, aka, walk in direction
                    stepsRemaining -= 1 // 1 less step remaining
                    positions.add(position) // Store new position
                    if(stepsRemaining <= 0) break
                }

                direction = turn(direction) // Turn 90 deg

                for (step in 1..currentStepsAmount) { // Walk current_width steps once
                    position = position.add(direction) // Add direction to position, aka, walk in direction
                    stepsRemaining -= 1 // 1 less step remaining
                    positions.add(position) // Store new position
                    if(stepsRemaining <= 0) break
                }

                direction = turn(direction) // Turn 90 deg

                currentStepsAmount += 1 // Increase the amount of steps to walk by 1
            }

            for (vector in positions) {
                val worldPos = center_position.add(Vec2toVec3(vector, plane))
                print("(" + worldPos.x + ", " + worldPos.y + ", " + worldPos.z + "), ")
            }
        }

        private fun turn(dir: Vec2): Vec2 {
            return if (dir.x == 1f && dir.y == 0f)
                Vec2(0f, 1f)
            else if (dir.x == 0f && dir.y == 1f)
                Vec2(-1f, 0f)
            else if (dir.x == -1f && dir.y == 0f)
                Vec2(0f, -1f)
            else
                Vec2(1f, 0f)
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
}
