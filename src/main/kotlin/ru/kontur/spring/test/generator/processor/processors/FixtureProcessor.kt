package ru.kontur.spring.test.generator.processor.processors

import ru.kontur.spring.test.generator.api.ValidationConstructor
import ru.kontur.spring.test.generator.exceptions.NoOptionalRecursiveException
import ru.kontur.spring.test.generator.extensions.isSimple
import ru.kontur.spring.test.generator.processor.AbstractGenerateProcessor
import ru.kontur.spring.test.generator.processor.GeneratorAnnotationScanner
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.isAccessible

/**
 * @author Konstantin Volivach
 */
class FixtureProcessor(
    private val constructors: Map<KClass<*>, ValidationConstructor<*>>,
    private val userPath: String
) : AbstractGenerateProcessor() {
    private val generatorAnnotationScanner = GeneratorAnnotationScanner()

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
        val constructor = if (clazz.isAbstract) {
            generatorAnnotationScanner.getSubTypeOf(clazz, userPath).kotlin.constructors.toMutableList()[0]
        } else {
            clazz.constructors.toMutableList()[0]
        }
        constructor.isAccessible = true
        val arguments = constructor.parameters.map { param ->
            val paramClazz = param.type.classifier as KClass<*>
            if (paramClazz == clazz) {
                if (param.isOptional) {
                    return@map null
                } else {
                    throw NoOptionalRecursiveException("Recursive field can't be required")
                }
            }
            generateParam(param.type.classifier as KClass<*>, param.type, null)
        }.toTypedArray()
        return constructor.call(*arguments)
    }
}