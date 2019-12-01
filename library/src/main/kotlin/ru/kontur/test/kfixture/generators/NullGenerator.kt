package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import javax.validation.constraints.Null
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = Null::class)
class NullGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return null
    }
}