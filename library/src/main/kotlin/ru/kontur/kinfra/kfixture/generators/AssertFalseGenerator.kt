package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.AssertFalse
import kotlin.reflect.KClass
import kotlin.reflect.KType

class AssertFalseGenerator : ValidParamGenerator<Boolean, AssertFalse> {
    override fun process(
        param: Boolean,
        annotation: AssertFalse,
        clazz: KClass<Boolean>,
        type: KType
    ): Boolean? {
        return false
    }
}