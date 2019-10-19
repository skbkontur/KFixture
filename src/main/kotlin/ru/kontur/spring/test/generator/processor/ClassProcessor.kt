package ru.kontur.spring.test.generator.processor

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.ValidateAnnotation
import ru.kontur.spring.test.generator.exceptions.NoSuchValidAnnotationException
import ru.kontur.spring.test.generator.generators.NotEmptyGenerator
import ru.kontur.spring.test.generator.utils.makeRandomInstance
import javax.validation.constraints.NotEmpty
import kotlin.reflect.KClass
import kotlin.reflect.full.starProjectedType

/**
 * @author Konstantin Volivach
 */
class ClassProcessor {

    fun generate(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val type = parameterContext.parameter.type
        return generateParam(type.kotlin)
    }

    private fun generateParam(type: KClass<*>): Any {
        return if (type.isSimple()) {
            processSimpleType(type)
        } else {
            val constructor = type.constructors.toMutableList()[0]
            val arguments = constructor.parameters.map {
                generateParam(it::class)
            }
            constructor.call(arguments)
        }
    }

    private fun processSimpleType(type: KClass<*>): Any {
        val annotations = type.annotations
        if (annotations.isEmpty()) {
            return makeRandomInstance(
                type,
                type.starProjectedType
            )!!
        } else {
            when (annotations[0].annotationClass) {
                NotEmpty::class -> {
                    return NotEmptyGenerator().process(null, type, type.starProjectedType)
                }
                ValidateAnnotation::class -> {
                    TODO("тут будет код")
                }
                else -> {
                    throw NoSuchValidAnnotationException("Please annotate your validate annotation with ValidateAnnotation class")
                }
            }
        }
    }

    private fun KClass<*>.isSimple(): Boolean {
        return this == Int::class || this == String::class || this == Boolean::class || this == List::class || this == Map::class
    }
}