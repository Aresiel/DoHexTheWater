package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.world.phys.Vec3

object OpListToVector : ConstManaOperator {
	override val argc = 1

	override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
		val list = args.getChecked<SpellList>(0, argc).toList()

		if (list.size != 3) {
			throw MishapInvalidIota(args[0], 0, "dohexthewater.mishap.not_vectory_list".asTranslatedComponent());
		}

		if(list[0].getType() != DatumType.DOUBLE || list[1].getType() != DatumType.DOUBLE || list[2].getType() != DatumType.DOUBLE) {
			throw MishapInvalidIota(args[0], 0, "dohexthewater.mishap.not_vectory_list".asTranslatedComponent());
		}

		val x = list[0].payload as Double
		val y = list[1].payload as Double
		val z = list[2].payload as Double

		return spellListOf(Vec3(x,y,z));
	}
}