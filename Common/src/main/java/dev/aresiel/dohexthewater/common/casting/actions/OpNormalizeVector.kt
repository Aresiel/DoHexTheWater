package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.ConstManaOperator
import at.petrak.hexcasting.api.spell.SpellDatum
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getChecked
import at.petrak.hexcasting.api.spell.spellListOf
import net.minecraft.Util
import net.minecraft.world.phys.Vec3

object OpNormalizeVector : ConstManaOperator {
	override val argc = 1

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val vector = args.getChecked<Vec3>(0, argc)
		val length = vector.length()
		val newVector = Vec3(vector.x/length, vector.y/length, vector.z/length)

		return spellListOf(newVector);
	}
}