package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ValidatorFor
import javax.validation.constraints.Null
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ValidatorFor(value = Null::class)
class NullGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return null
    }
}