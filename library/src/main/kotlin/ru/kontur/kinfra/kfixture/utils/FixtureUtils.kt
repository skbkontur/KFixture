package ru.kontur.kinfra.kfixture.utils

import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.scanner.StaticCache
import java.lang.RuntimeException
import java.lang.reflect.Modifier
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KVisibility
import kotlin.reflect.full.functions
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.javaMethod

object FixtureUtils {
    inline fun <reified T : Any> createClazz(vararg path: String): T {
        val scanner = StaticCache.getScanner(path.toList())
        val clazzProcessor = FixtureProcessor(CollectionSettings(), scanner)
        val result = clazzProcessor.generateParam(
            T::class, T::class.starProjectedType, null
        ) as? T
        return result ?: throw RuntimeException("Can't generate such clazz name=${T::class.simpleName}")
    }

    /**
     * Very slow methods
     */
    inline fun <reified V : Any> createList(vararg path: String): List<V> {
        return listOf(
            createClazz(*path)
        )
    }

    /**
     * Very slow methods
     */
    inline fun <reified K : Any, reified V : Any> createMap(vararg path: String): Map<K, V> {
        return mapOf(
            createClazz<K>(*path) to createClazz(*path)
        )
    }

    fun getCreatorFunction(clazz: KClass<*>, annotationScanner: GeneratorAnnotationScanner): KFunction<*> {
        val searchClazz = if (clazz.isAbstract || clazz.isSealed) {
            annotationScanner.getSubTypeOf(clazz)?.kotlin
                ?: annotationScanner.tryToGetSubTypeByAbstractPackage(clazz)?.kotlin
                ?: throw IllegalArgumentException(
                    "Please provide the path where to search subTypes of clazz=${clazz.qualifiedName}"
                )
        } else {
            clazz
        }

        val functions = if (!searchClazz.java.isKotlinClass()) {
            searchClazz.functions.filter {
                val method = it.javaMethod
                if (method == null) {
                    false
                } else {
                    it.returnType::classifier.get() == clazz && Modifier.isStatic(method.modifiers)
                }
            }
        } else {
            listOf()
        }

        val constructors = searchClazz.constructors

        val result = (functions + constructors).toMutableList()
        result.sortBy { it.parameters.size }
        result.sortBy { it.visibility ?: KVisibility.PUBLIC }
        return result[0]
    }

    private fun Class<*>.isKotlinClass(): Boolean {
        return declaredAnnotations.any { it.annotationClass.java.name == METADATA_NAME }
    }

    private const val METADATA_NAME = "kotlin.Metadata"
}