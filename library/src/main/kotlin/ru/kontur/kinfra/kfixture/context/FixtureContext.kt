package ru.kontur.kinfra.kfixture.context

import ru.kontur.kinfra.kfixture.processor.AbstractGenerateProcessor
import java.lang.RuntimeException
import kotlin.reflect.KClass
import kotlin.reflect.full.starProjectedType

class FixtureContext(
    val processor: AbstractGenerateProcessor
) {
    fun <T : Any> createClazz(clazz: KClass<T>): T {
        val result = processor.generateParam(
            clazz, clazz.starProjectedType, null
        ) as? T
        return result ?: throw RuntimeException("Can't generate such clazz name=${clazz.simpleName}")
    }

    fun <V : Any> createList(clazz: KClass<V>): List<V> {
        return listOf(
            createClazz(clazz)
        )
    }

    fun <K : Any, V : Any> createMap(key: KClass<K>, value: KClass<V>): Map<K, V> {
        return mapOf(
            createClazz(key) to createClazz(value)
        )
    }
}