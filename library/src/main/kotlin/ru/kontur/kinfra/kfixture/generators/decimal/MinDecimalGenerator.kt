package ru.kontur.kinfra.kfixture.generators.decimal

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.generators.VariableCreator
import ru.kontur.kinfra.kfixture.generators.operators.PlusSupplier
import javax.validation.constraints.DecimalMin

class MinDecimalGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>,
    private val plusSupplyer: PlusSupplier<T>
) : ValidParamGenerator<T, DecimalMin> {

    override fun process(param: T, annotation: DecimalMin): T? {
        val min = creator.create(annotation.value.toLong())

        return if (param < min) {
            plusSupplyer.plus(min, creator.create(1))
        } else {
            param
        }
    }
}