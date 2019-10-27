package ru.kontur.spring.test.generator.processor

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.ValidateAnnotation
import ru.kontur.spring.test.generator.api.ValidationParamResolver
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
    private val generators: Map<KClass<out Annotation>, ValidationParamResolver> = mapOf(
        AssertFalse::class to AssertFalseGenerator(),
        AssertTrue::class to AssertTrueGenerator(),
        DecimalMax::class to DecimalMaxGenerator(),
        DecimalMin::class to DecimalMinGenerator(),
        Digits::class to DigitsGenerator(),
        Email::class to EmailGenerator(),
        Future::class to FutureGenerator(),
        FutureOrPresent::class to FutureOrPresentGenerator(),
        Max::class to MaxGenerator(),
        Min::class to MinGenerator(),
        Negative::class to NegativeGenerator(),
        NegativeOrZero::class to NegativeOrZeroGenerator(),
        NotBlank::class to NotBlankGenerator(),
        NotEmpty::class to NotEmptyGenerator(),
        NotNull::class to NotNullGenerator(),
        Null::class to NullGenerator(),
        Past::class to PastGenerator(),
        PastOrPresent::class to PastOrPresentGenerator(),
        Pattern::class to PatternGenerator(),
        Positive::class to PositiveGenerator(),
        PositiveOrZero::class to PositiveOrZeroGenerator(),
        Size::class to SizeGenerator()
    )

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
            constructor.call(*arguments)
        }
    }

    private fun processSimpleType(clazz: KClass<*>, type: KType, annotation: Annotation?): Any {
        return if (annotation == null) {
            makeRandomInstance(
                clazz,
                type
            )!!
        } else {
            if (annotation.annotationClass == ValidateAnnotation::class) {
                TODO("тут будет код")
            } else {
                val generator = generators[annotation.annotationClass]
                generator?.process(null, clazz, type, annotation)
                    ?: throw NoSuchValidAnnotationException("Please annotate your validate annotation with ValidateAnnotation class")
            }
        }
    }

    private fun KClass<*>.isSimple(): Boolean {
        return this == Int::class || this == String::class || this == Boolean::class || this == List::class || this == Map::class
    }
}