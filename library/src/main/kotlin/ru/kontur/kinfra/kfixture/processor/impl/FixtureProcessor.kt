package ru.kontur.kinfra.kfixture.processor.impl

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.exceptions.NoOptionalRecursiveException
import ru.kontur.kinfra.kfixture.extensions.isSimple
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.processor.AbstractGenerateProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.utils.FixtureUtils
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.isAccessible

/**
 * @author Konstantin Volivach
 */
class FixtureProcessor(
    override val collectionSettings: CollectionSettings,
    private val generatorAnnotationScanner: GeneratorAnnotationScanner
) : AbstractGenerateProcessor() {
    private val constructors: Map<KClass<*>, ParamConstructor<*>> = generatorAnnotationScanner.getConstructors()

    override fun generateParam(clazz: KClass<*>, type: KType, annotation: List<Annotation>?): Any? {
        return when {
            clazz.isSimple() -> generatePrimitiveValue(clazz, type, null)
            clazz.java.isEnum -> {
                val x = Random.nextInt(clazz.java.enumConstants.size)
                clazz.java.enumConstants[x]
            }
            else -> {
                when {
                    constructors.containsKey(clazz) -> constructors[clazz]?.call()
                    else -> {
                        createClazz(clazz)
                    }
                }
            }
        }
    }

    private fun createClazz(clazz: KClass<*>): Any {
        val constructor = FixtureUtils.getCreatorFunction(clazz, generatorAnnotationScanner)
        constructor.isAccessible = true
        val parameters = constructor.parameters
        val arguments = Array<Any?>(parameters.size) {}
        for (i in arguments.indices) {
            val param = parameters[i]
            val paramClazz = param.type.classifier as KClass<*>
            if (paramClazz == clazz) {
                if (param.isOptional) {
                    arguments[i] = null
                    continue
                } else {
                    throw NoOptionalRecursiveException("Recursive field can't be required ${param::class.simpleName}")
                }
            }
            if (paramClazz == List::class) {
                if (param.type.arguments.isNotEmpty() && param.type.arguments[0].type?.classifier == clazz) {
                    arguments[i] = listOf<Nothing>()
                    continue
                }
            }
            arguments[i] = generateParam(paramClazz, param.type, null)
        }
        return requireNotNull(constructor.call(*arguments)) {
            "Constructor of your clazz is null, clazz=${clazz.simpleName}, it can happens if one of your static function return null"
        }
    }
}