package ru.kontur.spring.test.generator.processor

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.ValidationConstructor
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.constructors.UUIDConstructor
import ru.kontur.spring.test.generator.exceptions.NoSuchValidAnnotationException
import ru.kontur.spring.test.generator.utils.*
import java.lang.RuntimeException
import java.util.*
import javax.validation.constraints.*
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class ClassProcessor(private val userPath: String) {
    // TODO scan and this generators
    private val generators: Map<KClass<out Annotation>, ValidationParamResolver>
    private val constructors: Map<KClass<*>, ValidationConstructor<*>> = mapOf(
        UUID::class to UUIDConstructor()
    )

    init {
        val customAnnotationProcessor = GeneratorAnnotationScanner(userPath)
        val validatorsMap = customAnnotationProcessor.getValidatorsMap()
        generators = validatorsMap
    }

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

    fun generate(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val type = parameterContext.parameter.type
        return generateParam(type.kotlin, type.toKType(), null)
            ?: throw RuntimeException("Something went wrong")
    }

    private fun generateParam(clazz: KClass<*>, type: KType, annotation: List<Annotation>?): Any? {
        return when {
            clazz.isSimple() -> processSimpleType(clazz, type, annotation)
            clazz.java.isEnum -> {
                val x = Random.nextInt(clazz.java.enumConstants.size)
                clazz.java.enumConstants[x]
            }
            else -> {
                if (constructors.containsKey(clazz)) {
                    constructors[clazz]?.call()
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
        }
    }

    private fun processSimpleType(clazz: KClass<*>, type: KType, annotationList: List<Annotation>?): Any? {
        return when {
            annotationList == null || annotationList.isEmpty() -> generatePrimitiveValue(
                clazz,
                type
            )
            annotationList.any {
                generators.keys.contains(it::class)
            } -> {
                val sorted = annotationList.sortedBy {
                    return@sortedBy defaultPriority[it.annotationClass]
                }
                var generatedParam: Any? = null
                for (annotation in sorted) {
                    val generator = generators[annotation.annotationClass]
                        ?: throw NoSuchValidAnnotationException("Please annotate your validate annotation with ValidateAnnotation class")
                    generatedParam = generator.process(generatedParam, clazz, type, annotation)
                }
            }
            else -> {
                generateParam(clazz, type, annotationList)
            }
        }
    }

    private fun KClass<*>.isSimple(): Boolean {
        return this == Int::class || this == Long::class || this == String::class || this == Boolean::class || this == List::class || this == Map::class
    }
}