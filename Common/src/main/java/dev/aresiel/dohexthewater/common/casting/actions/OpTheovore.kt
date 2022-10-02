package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import net.minecraft.world.phys.Vec2
import kotlin.math.roundToInt

object OpTheovore : SpellOperator {
	override val argc = 3
	override val isGreat = true

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val patternList = args.getChecked<SpellList>(0, argc)
		val stepRadius = args.getChecked<Double>(1, argc).roundToInt()
		val stepSize = args.getChecked<Double>(2, argc).roundToInt()


		return Triple(Spell(ctx, patternList, stepRadius, stepSize), stepRadius*stepRadius, listOf())
	}

	private data class Spell(val ctx: CastingContext, val patterList: SpellList, val stepRadius: Int, val stepSize: Int): RenderedSpell {
		override fun cast(ctx: CastingContext) {
			var position = Vec2(0f,0f)
			var steps = 1
			var direction = Vec2(0f,1f)
			while(true) {
				for (step in 1..2) {
					position = walk(position, direction)
				}
				direction = turn(direction)
				for (step in 1..steps) {
					position = walk(position, direction)
				}
				direction = turn(direction)
				steps += 1
			}

		}
	}

	private fun walk(position: Vec2, direction: Vec2): Vec2 { // WALK steps IN direction
		return Vec2(position.x+direction.x, position.y+direction.y)
	}
	private fun turn(direction: Vec2): Vec2 { // Turn clockwise
		when (direction) { //TURN
			Vec2(0f, 1f) -> return Vec2(-1f, 0f)
		}
		return Vec2(0f, -1f)
	}
}