package ru.kontur.kinfra.kfixture.processor.impl

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import ru.kontur.kinfra.kfixture.exceptions.NoOptionalRecursiveException
import ru.kontur.kinfra.kfixture.exceptions.NoSuchValidAnnotationException
import ru.kontur.kinfra.kfixture.extensions.isSimple
import ru.kontur.kinfra.kfixture.processor.AbstractGenerateProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.routers.ValidRouter
import ru.kontur.kinfra.kfixture.utils.FixtureUtils
import javax.validation.Constraint
import javax.validation.constraints.*
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class JavaxFixtureProcessor(
    private val generators: Map<KClass<out Annotation>, ValidRouter<Any, Any>>,
    private val validationConstructors: Map<KClass<*>, ValidationConstructor<*>>,
    private val constructors: Map<KClass<*>, ParamConstructor<*>>,
    private val generatorAnnotationScanner: GeneratorAnnotationScanner,
    private val fixtureProcessor: FixtureProcessor
) : AbstractGenerateProcessor() {

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

    override fun generateParam(clazz: KClass<*>, type: KType, annotation: List<Annotation>?): Any? {
        val annotationSum = (annotation?.let { it + clazz.annotations }
            ?: clazz.annotations).filter { it.annotationClass.annotations.any { annotation -> annotation is Constraint } }

        return when {
            clazz.isSimple() -> processSimpleType(clazz, type, annotationSum)
            clazz.java.isEnum -> {
                val x = Random.nextInt(clazz.java.enumConstants.size)
                clazz.java.enumConstants[x]
            }
            else -> {
                when {
                    validationConstructors.containsKey(clazz) -> validationConstructors[clazz]?.call()
                    !annotationSum.isNullOrEmpty() -> {
                        var result = fixtureProcessor.generateParam(clazz, type, annotation)
                        for (it in annotationSum) {
                            val generator = generators[it.annotationClass]
                            result = if (generator != null) {
                                generator.process(result!!, it, clazz, type)
                            } else {
                                createClazz(clazz) // Else create by default generators
                            }
                        }
                        return result
                    }
                    else -> {
                        createClazz(clazz)
                    }
                }
            }
        }
    }

    private fun createClazz(clazz: KClass<*>): Any {
        val userConstructor = constructors[clazz]
        if (userConstructor != null) {
            return userConstructor.call()
        }

        val constructor = FixtureUtils.getCreatorFunction(clazz, generatorAnnotationScanner)
        val arguments = constructor.parameters.map { param ->
            val newAnnotation =
                clazz.java.declaredFields.firstOrNull { it.name == param.name }?.annotations?.toList()
            val paramClazz = param.type.classifier as KClass<*>
            if (paramClazz == clazz) {
                if (param.isOptional) {
                    return@map null
                } else {
                    throw NoOptionalRecursiveException("Recursive field can't be required field.name=${param.name} clazz=$paramClazz")
                }
            }
            generateParam(param.type.classifier as KClass<*>, param.type, newAnnotation)
        }.toTypedArray()
        return requireNotNull(constructor.call(*arguments)) {
            "Constructor of your clazz is null, clazz=${clazz.simpleName}, it can happens if one of your static function return null"
        }
    }

    private fun processSimpleType(clazz: KClass<*>, type: KType, annotationList: List<Annotation>?): Any? {
        return when {
            annotationList != null && annotationList.any {
                generators.keys.contains(it.annotationClass)
            } -> {
                val sorted = annotationList.sortedBy {
                    return@sortedBy defaultPriority[it.annotationClass]
                }
                var generatedParam = fixtureProcessor.generateParam(clazz, type, listOf())
                for (annotation in sorted) {
                    val generator = generators[annotation.annotationClass]
                        ?: throw NoSuchValidAnnotationException("Please annotate your validate annotation with ValidateAnnotation class")
                    generatedParam = generator.process(generatedParam!!, annotation, clazz, type)
                }
                generatedParam
            }
            else -> {
                this.generatePrimitiveValue(clazz, type, annotationList)
            }
        }
    }
}