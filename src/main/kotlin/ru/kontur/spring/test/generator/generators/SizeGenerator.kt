package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.utils.generateCollection
import ru.kontur.spring.test.generator.utils.generateMap
import ru.kontur.spring.test.generator.utils.generateString
import javax.validation.constraints.Size
import kotlin.reflect.KClass
import kotlin.reflect.KType

class SizeGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        val sizeAnnotation = (annotation as Size)

        val value = if (sizeAnnotation.min == sizeAnnotation.max) {
            sizeAnnotation.min
        } else {
            sizeAnnotation.min + 1
        }

        when (clazz) {
            String::class -> {
                return generateString(value)
            }
            Collection::class -> {
                return generateCollection(value, clazz, type)
            }
            Map::class -> {
                return generateMap(value, clazz, type)
            }
        }
        return generatedParam
    }
}