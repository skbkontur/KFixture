package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.api.ValidationParamResolver
import javax.validation.constraints.AssertTrue
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = AssertTrue::class)
class AssertTrueGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return true
    }
}