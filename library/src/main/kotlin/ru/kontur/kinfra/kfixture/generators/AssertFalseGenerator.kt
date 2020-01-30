package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import ru.kontur.kinfra.kfixture.api.ResolverFor
import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.AssertFalse
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = AssertFalse::class)
class AssertFalseGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return false
    }
}

class AssertFalseGenerator2 : ValidParamGenerator<Boolean, AssertFalse> {
    override fun process(param: Boolean?, annotation: AssertFalse): Boolean {
        return false
    }
}