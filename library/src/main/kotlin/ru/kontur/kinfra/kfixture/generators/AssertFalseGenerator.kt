package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import ru.kontur.kinfra.kfixture.api.ResolverFor
import javax.validation.constraints.AssertFalse
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = AssertFalse::class)
class AssertFalseGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return false
    }
}