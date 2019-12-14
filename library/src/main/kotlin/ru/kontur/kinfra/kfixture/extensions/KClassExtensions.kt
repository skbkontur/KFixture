package ru.kontur.kinfra.kfixture.extensions

import kotlin.reflect.KClass

internal fun KClass<*>.isSimple(): Boolean {
    return this == Int::class || this == Long::class || this == String::class || this == Boolean::class || this == List::class || this == Map::class
}