package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.Util
import net.minecraft.world.phys.Vec3
import java.util.DoubleSummaryStatistics
import kotlin.math.roundToInt

object OpRange : ConstManaOperator {
	override val argc = 2

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val start = args.getChecked<Double>(0, argc).roundToInt()
		val stop = args.getChecked<Double>(1, argc).roundToInt()

		if(start > stop) throw MishapInvalidIota(args[0], 1, "dohexthewater.mishap.stop_smaller_than_start".asTranslatedComponent())

		return (start .. stop).map(Int::toDouble).map(SpellDatum.Companion::make).asSpellResult

	}
}