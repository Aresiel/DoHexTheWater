package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import kotlin.math.roundToInt

object OpRandInt : ConstManaOperator {
	override val argc = 2

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val lowerBound = args.getChecked<Double>(0, argc).roundToInt()
		val upperBound = args.getChecked<Double>(1, argc).roundToInt()

		if(lowerBound > upperBound) throw MishapInvalidIota(args[0], 0, "dohexthewater.mishap.greater_lower_bound".asTranslatedComponent())

		return spellListOf((lowerBound..upperBound).random().toDouble())
	}
}