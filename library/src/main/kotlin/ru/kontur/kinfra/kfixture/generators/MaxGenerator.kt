package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.generators.operators.MinusSupplier
import javax.validation.constraints.Max

/**
 * @author Konstantin Volivach
 */
class MaxGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>,
    private val minusSupplier: MinusSupplier<T>
) : ValidParamGenerator<T, Max> {
    override fun process(param: T, annotation: Max): T {
        val max = creator.create(annotation.value)

        return if (param > max) {
            minusSupplier.minus(max, creator.create(1))
        } else {
            param
        }
    }
}