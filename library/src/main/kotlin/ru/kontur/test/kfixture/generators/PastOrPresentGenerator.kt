package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import javax.validation.constraints.PastOrPresent
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = PastOrPresent::class)
class PastOrPresentGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}