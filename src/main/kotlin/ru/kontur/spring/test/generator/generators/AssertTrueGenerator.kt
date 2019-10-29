package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ValidatorFor
import javax.validation.constraints.AssertTrue
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ValidatorFor(value = AssertTrue::class)
class AssertTrueGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return true
    }
}