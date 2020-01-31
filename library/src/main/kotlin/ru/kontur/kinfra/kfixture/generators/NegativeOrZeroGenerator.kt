package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.NegativeOrZero

class NegativeOrZeroGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, NegativeOrZero> {
    override fun process(param: T?, annotation: NegativeOrZero): T {
        return if (param == null || param > creator.create(0)) {
            return creator.create(DEFAULT_VALUE)
        } else {
            param
        }
    }

    private companion object {
        const val DEFAULT_VALUE = 0L
    }
}