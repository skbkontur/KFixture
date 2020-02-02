package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Null
import kotlin.reflect.KClass
import kotlin.reflect.KType

class NullGenerator<T : Any> : ValidParamGenerator<T, Null> {
    override fun process(
        param: T,
        annotation: Null,
        clazz: KClass<T>,
        type: KType
    ): T? {
        return null
    }
}