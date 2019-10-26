package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FutureOrPresentGenerator: ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}