package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.NegativeOrZero
import kotlin.reflect.KClass
import kotlin.reflect.KType

class NegativeOrZeroGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, NegativeOrZero> {
    override fun process(
        param: T,
        annotation: NegativeOrZero,
        clazz: KClass<*>,
        type: KType
    ): T? {
        return if (param > creator.create(0)) {
            return creator.create(DEFAULT_VALUE)
        } else {
            param
        }
    }

    private companion object {
        const val DEFAULT_VALUE = 0L
    }
}