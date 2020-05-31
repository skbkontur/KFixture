package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KType

class NotNullGenerator<T : Any> : ValidParamGenerator<T, NotNull> {
    override fun process(
        param: T,
        annotation: NotNull,
        clazz: KClass<*>,
        type: KType
    ): T? {
        return param
    }
}