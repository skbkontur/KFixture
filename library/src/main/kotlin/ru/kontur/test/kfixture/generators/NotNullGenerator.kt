package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.utils.makeRandomInstance
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