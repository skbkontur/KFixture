package ru.kontur.spring.test.generator.processor

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.ValidateAnnotation
import ru.kontur.spring.test.generator.exceptions.NoSuchValidAnnotationException
import ru.kontur.spring.test.generator.generators.*
import ru.kontur.spring.test.generator.utils.makeRandomInstance
import ru.kontur.spring.test.generator.utils.toKType
import javax.validation.constraints.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class ClassProcessor {
    fun generate(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val type = parameterContext.parameter.type
        return generateParam(type.kotlin, type.toKType(), null)
    }

    private fun generateParam(clazz: KClass<*>, type: KType, annotation: Annotation?): Any {
        return if (clazz.isSimple()) {
            processSimpleType(clazz, type, annotation)
        } else {
            val constructor = clazz.constructors.toMutableList()[0]
            val arguments = constructor.parameters.map { param ->
                val newAnnotation =
                    clazz.java.declaredFields.firstOrNull { it.name == param.name }?.annotations?.getOrNull(0)
                generateParam(param.type.classifier as KClass<*>, param.type, newAnnotation)
            }.toTypedArray()
            println("Recursion call")
            constructor.call(*arguments)
        }
    }

    private fun processSimpleType(clazz: KClass<*>, type: KType, annotation: Annotation?): Any {
        if (annotation == null) {
            return makeRandomInstance(
                clazz,
                type
            )!!
        } else {
            when (annotation.annotationClass) {
                NotEmpty::class -> {
                    val generator = NotEmptyGenerator()
                    return generator.process(null, clazz, type)
                }
                Email::class -> {
                    val generator = EmailGenerator()
                    return generator.process(null, clazz, type)
                }
                Min::class -> {
                    val value = (annotation as Min).value
                    val generator = MinGenerator(value)
                    return generator.process(null, clazz, type)
                }
                Max::class -> {
                    val value = (annotation as Max).value
                    val generator = MaxGenerator(value)
                    return generator.process(null, clazz, type)
                }
                NotBlank::class -> {
                    val generator = NotBlankGenerator()
                    return generator.process(null, clazz, type)
                }
                Negative::class -> {
                    val generator = NegativeGenerator()
                    return generator.process(null, clazz, type)
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