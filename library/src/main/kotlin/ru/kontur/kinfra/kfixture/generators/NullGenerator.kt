package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import ru.kontur.kinfra.kfixture.api.ResolverFor
import javax.validation.constraints.Null
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = Null::class)
class NullGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return null
    }
}