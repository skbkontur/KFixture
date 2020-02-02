package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Positive
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstatntin Volivach
 */
class PositiveGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, Positive> {
    override fun process(
        param: T,
        annotation: Positive,
        clazz: KClass<T>,
        type: KType
    ): T? {
        if (param <= creator.create(0)) {
            return creator.create(DEFAULT_VALUE)
        }
        return param
    }

    companion object {
        private const val DEFAULT_VALUE = 10L
    }
}