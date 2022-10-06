package dev.aresiel.dohexthewater.common.casting.actions

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.casting.SpellContinuation
import net.minecraft.Util
import net.minecraft.world.phys.Vec3

object OpHaltIfZero : Operator {

    override fun operate(
        continuation: SpellContinuation,
        stack: MutableList<SpellDatum<*>>,
        local: SpellDatum<*>,
        ctx: CastingContext
    ): OperationResult {

        val input = stack.getChecked<Double>(stack.size-1)
        stack.removeAt(stack.size-1)

        if (input == 0.0) {
            var newStack = stack.toList()
            var done = false
            var newCont = continuation
            while (!done && newCont is SpellContinuation.NotDone) {
                val newInfo = newCont.frame.breakDownwards(newStack)
                done = newInfo.first
                newStack = newInfo.second
                newCont = newCont.next
            }
            if (!done) {
                newStack = listOf()
            }

            return OperationResult(newCont, newStack, local, listOf())
        } else
            return OperationResult(continuation, stack, local, listOf())
    }
}