package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.utils.generateCollection
import ru.kontur.test.kfixture.utils.generateMap
import ru.kontur.test.kfixture.utils.generateString
import javax.validation.constraints.Size
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = Size::class)
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