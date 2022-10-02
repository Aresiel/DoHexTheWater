package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import net.minecraft.Util
import net.minecraft.world.phys.Vec3

object OpVectorToList : ConstManaOperator {
	override val argc = 1

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val vector = args.getChecked<Vec3>(0, argc)

		return spellListOf(spellListOf(vector.x, vector.y, vector.z));
	}
}