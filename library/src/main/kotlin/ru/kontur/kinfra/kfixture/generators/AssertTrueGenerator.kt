package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.AssertTrue
import kotlin.reflect.KClass
import kotlin.reflect.KType

class AssertTrueGenerator : ValidParamGenerator<Boolean, AssertTrue> {
    override fun process(
        param: Boolean,
        annotation: AssertTrue,
        clazz: KClass<Boolean>,
        type: KType
    ): Boolean? {
        return true
    }
}