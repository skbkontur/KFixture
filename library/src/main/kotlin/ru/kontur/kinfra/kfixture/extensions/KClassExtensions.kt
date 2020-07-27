package ru.kontur.kinfra.kfixture.extensions

import kotlin.reflect.KClass

internal fun KClass<*>.isSimple(): Boolean {
    return this.simpleName in SIMPLE_LIST
}

internal val ARRAYS_NAMES = listOf(
    "Array", "ByteArray", "LongArray", "IntArray", "ShortArray", "DoubleArray", "FloatArray"
)

private val SIMPLE_LIST = listOf(
    Int::class.simpleName, Long::class.simpleName, String::class.simpleName,
    Boolean::class.simpleName, List::class.simpleName, Map::class.simpleName,
    Double::class.simpleName, Short::class.simpleName,
    Float::class.simpleName, Byte::class.simpleName, Set::class.simpleName
) + ARRAYS_NAMES