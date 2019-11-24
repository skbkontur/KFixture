package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import javax.validation.constraints.Past
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = Past::class)
class PastGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}