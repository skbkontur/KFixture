package ru.kontur.kinfra.kfixture.generators.decimal

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.generators.VariableCreator
import ru.kontur.kinfra.kfixture.generators.operators.MinusSupplier
import javax.validation.constraints.DecimalMax

class MaxDecimalGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>,
    private val minusOperation: MinusSupplier<T>
) : ValidParamGenerator<T, DecimalMax> {
    override fun process(param: T, annotation: DecimalMax): T? {
        val max = creator.create(annotation.value.toLong())

        return if (param > max) {
            minusOperation.minus(max, creator.create(1))
        } else {
            param
        }
    }
}