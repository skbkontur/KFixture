package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ResolverFor
import ru.kontur.spring.test.generator.utils.makeRandomInstance
import javax.validation.constraints.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = NotNull::class)
class NotNullGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        if (generatedParam != null) {
            return generatedParam
        }
        return makeRandomInstance(clazz, type)
    }
}