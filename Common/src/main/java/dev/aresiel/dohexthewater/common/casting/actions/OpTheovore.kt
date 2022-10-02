package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext

object OpTheovore : SpellOperator {
	override val argc = 3
	override val isGreat = true

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val patternList = args.getChecked<SpellList>(0, argc)
		val stepRadius = args.getChecked<Int>(1, argc)
		val stepSize = args.getChecked<Int>(2, argc)


		return Triple(Spell(ctx, patternList, stepRadius, stepSize), stepRadius*stepRadius, listOf())
	}

	private data class Spell(val ctx: CastingContext, val patterList: SpellList, val stepRadius: Int, val stepSize: Int): RenderedSpell {
		override fun cast(ctx: CastingContext) {
		}
	}
}