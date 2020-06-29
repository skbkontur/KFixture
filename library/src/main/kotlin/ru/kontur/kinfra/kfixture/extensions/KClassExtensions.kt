package ru.kontur.kinfra.kfixture.extensions

import kotlin.reflect.KClass

internal fun KClass<*>.isSimple(): Boolean {
    return this == Int::class || this == Long::class || this == String::class ||
            this == Boolean::class || this == List::class || this == Map::class ||
            this == Double::class || this == Short::class ||
            this == Float::class || this.simpleName in ARRAYS_NAMES || this == Byte::class
}

internal val ARRAYS_NAMES = listOf<String>(
    "Array", "ByteArray", "LongArray", "IntArray", "ShortArray", "DoubleArray", "FloatArray"
)