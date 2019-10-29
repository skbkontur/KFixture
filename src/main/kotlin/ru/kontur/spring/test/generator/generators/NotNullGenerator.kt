package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.utils.makeRandomInstance
import kotlin.reflect.KClass
import kotlin.reflect.KType

class NotNullGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        if (generatedParam!=null) {
            return generatedParam
        }
        return makeRandomInstance(clazz, type)
    }
}