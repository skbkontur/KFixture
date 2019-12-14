package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ResolverFor
import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import javax.validation.constraints.AssertTrue
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = AssertTrue::class)
class AssertTrueGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return true
    }
}