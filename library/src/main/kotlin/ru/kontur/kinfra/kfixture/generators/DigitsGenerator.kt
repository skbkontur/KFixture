package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits
import kotlin.reflect.KClass
import kotlin.reflect.KType

class DigitsGenerator<T : Any>(
    private val creator: DigitsCreator<T>
) : ValidParamGenerator<T, Digits> {

    override fun process(
        param: T,
        annotation: Digits,
        clazz: KClass<*>,
        type: KType
    ): T? {
        return creator.create(annotation.integer - 1, annotation.fraction - 1)
    }
}