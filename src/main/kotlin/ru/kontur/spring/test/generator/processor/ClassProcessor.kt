package ru.kontur.spring.test.generator.processor

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.ValidateAnnotation
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.exceptions.NoSuchValidAnnotationException
import ru.kontur.spring.test.generator.generators.*
import ru.kontur.spring.test.generator.utils.makeRandomInstance
import ru.kontur.spring.test.generator.utils.toKType
import java.lang.RuntimeException
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

    private val defaultPriority: Map<KClass<out Annotation>, Long> = mapOf(
            AssertFalse::class to 0L,
            AssertTrue::class to 0L,
            DecimalMax::class to 0L,
            DecimalMin::class to 0L,
            Digits::class to 0L,
            Email::class to 0L,
            Future::class to 0L,
            FutureOrPresent::class to 0L,
            Max::class to 0L,
            Min::class to 0L,
            Negative::class to 0L,
            NegativeOrZero::class to 0L,
            NotBlank::class to -10L,
            NotEmpty::class to -10L,
            NotNull::class to -10L,
            Null::class to -10L,
            Past::class to 0L,
            PastOrPresent::class to 0L,
            Pattern::class to 0L,
            Positive::class to 0L,
            PositiveOrZero::class to 0L,
            Size::class to 0L
    )

    private lateinit var usersGenerators: Map<KClass<out Annotation>, ValidationParamResolver>

    fun generate(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val type = parameterContext.parameter.type
        return generateParam(type.kotlin, type.toKType(), null)
                ?: throw RuntimeException("Something went wrong")
    }

    private fun generateParam(clazz: KClass<*>, type: KType, annotation: List<Annotation>?): Any? {
        return if (clazz.isSimple()) {
            processSimpleType(clazz, type, annotation)
        } else {
            val constructor = clazz.constructors.toMutableList()[0]
            val arguments = constructor.parameters.map { param ->
                val newAnnotation =
                        clazz.java.declaredFields.firstOrNull { it.name == param.name }?.annotations?.toList()
                generateParam(param.type.classifier as KClass<*>, param.type, newAnnotation)
            }.toTypedArray()
            constructor.call(*arguments)
        }
    }

    private fun processSimpleType(clazz: KClass<*>, type: KType, annotationList: List<Annotation>?): Any? {
        return if (annotationList == null) {
            makeRandomInstance(
                    clazz,
                    type
            )
        } else {
            val sorted = annotationList.sortedBy {
                return@sortedBy defaultPriority[it.annotationClass]
            }
            var generatedParam: Any? = null
            for (annotation in sorted) {
                generatedParam = if (annotation.annotationClass == ValidateAnnotation::class) {
                    val generator = usersGenerators[annotation.annotationClass]
                            ?: throw NoSuchValidAnnotationException("Please annotate your validate annotation with ValidateAnnotation class")
                    generator.process(generatedParam, clazz, type, annotation)
                } else {
                    val generator = generators[annotation.annotationClass]
                            ?: throw NoSuchValidAnnotationException("Please annotate your validate annotation with ValidateAnnotation class")
                    generator.process(generatedParam, clazz, type, annotation)

                }
            }
        }
    }

    private fun KClass<*>.isSimple(): Boolean {
        return this == Int::class || this == String::class || this == Boolean::class || this == List::class || this == Map::class
    }
}