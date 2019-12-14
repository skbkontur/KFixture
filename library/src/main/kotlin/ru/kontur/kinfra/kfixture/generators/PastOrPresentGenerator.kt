package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import ru.kontur.kinfra.kfixture.api.ResolverFor
import javax.validation.constraints.PastOrPresent
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = PastOrPresent::class)
class PastOrPresentGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}