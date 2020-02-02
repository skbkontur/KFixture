package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.PositiveOrZero
import kotlin.reflect.KClass
import kotlin.reflect.KType

class PositiveOrZeroGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, PositiveOrZero> {
    override fun process(
        param: T,
        annotation: PositiveOrZero,
        clazz: KClass<T>,
        type: KType
    ): T? {
        if (param < creator.create(0)) {
            return creator.create(DEFAULT_VALUE)
        }
        return param
    }

    companion object {
        private const val DEFAULT_VALUE = 10L
    }
}