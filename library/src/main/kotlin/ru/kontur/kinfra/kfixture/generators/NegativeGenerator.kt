package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Negative
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class NegativeGenerator<T : Comparable<T>>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, Negative> {
    override fun process(
        param: T,
        annotation: Negative,
        clazz: KClass<*>,
        type: KType
    ): T? {
        return if (param > creator.create(0)) {
            creator.create(DEFAULT_MINUS)
        } else {
            param
        }
    }

    private companion object {
        const val DEFAULT_MINUS = -1L
    }
}