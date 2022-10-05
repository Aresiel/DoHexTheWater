package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.world.phys.Vec3
import kotlin.math.abs

object OpPositiveVector : ConstManaOperator {
	override val argc = 1

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val vector = args.getChecked<Vec3>(0, argc)

		return spellListOf(Vec3(abs(vector.x), abs(vector.y), abs(vector.z)))
	}
}