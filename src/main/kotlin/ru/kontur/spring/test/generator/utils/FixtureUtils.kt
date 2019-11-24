package ru.kontur.spring.test.generator.utils

import ru.kontur.spring.test.generator.processor.GeneratorAnnotationScanner
import ru.kontur.spring.test.generator.processor.processors.FixtureProcessor
import java.lang.RuntimeException
import java.lang.reflect.Modifier
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.javaMethod

object FixtureUtils {
    inline fun <reified T : Any> createClazz(vararg path: String): T {
        val generatorAnnotationScanner = GeneratorAnnotationScanner(path.toList() + listOf(LIBRARY_PATH))
        val constructors = generatorAnnotationScanner.getConstructors()
        val clazzProcessor = FixtureProcessor(constructors, generatorAnnotationScanner)
        val result = clazzProcessor.generateParam(
            T::class, T::class.starProjectedType
            , null
        ) as? T
        return result ?: throw RuntimeException("Can't generate such clazz name=${T::class.simpleName}")
    }

    inline fun <reified V : Any> createList(vararg path: String): List<V> {
        return listOf(
            createClazz(*path)
        )
    }

    inline fun <reified K : Any, reified V : Any> createMap(vararg path: String): Map<K, V> {
        return mapOf(
            createClazz<K>(*path) to createClazz(*path)
        )
    }

    fun getCreatorFunction(clazz: KClass<*>, annotationScanner: GeneratorAnnotationScanner): KFunction<*> {
        val searchClazz = if (clazz.isAbstract) {
            annotationScanner.getSubTypeOf(clazz).kotlin
        } else {
            clazz
        }

        val functions =
            searchClazz.functions.filter {
                val method = it.javaMethod
                if (method == null) {
                    false
                } else {
                    it.returnType::classifier.get() == clazz && Modifier.isStatic(method.modifiers)
                }
            }
        val constructors = searchClazz.constructors

        val result = (functions + constructors).toMutableList()
        result.sortBy { it.parameters.size }
        return result[0]
    }

    const val LIBRARY_PATH = "ru.kontur.spring.test.generator"
}