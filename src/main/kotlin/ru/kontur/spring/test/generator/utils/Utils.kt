package ru.kontur.spring.test.generator.utils

import ru.kontur.spring.test.generator.exceptions.NoPrimitiveTypeException
import kotlin.random.Random
import kotlin.reflect.KClass

fun generateString(): String {
    TODO("Not implemented")
}

fun generateDouble(): Double {
    return Random.nextDouble()
}

fun generateMap(keyClass: KClass<Any>, valueClass: KClass<Any>): Map<Any, Any> {
    TODO("Not implemented")
}

fun generateCollection(keyClass: KClass<Any>): Any {
    TODO("Not implemented")
}

fun generatePrimitiveValue(kclass: KClass<Any>): Any {
    return when (kclass) {
        Double::class -> {

        }
        Int::class -> {

        }
        Float::class -> {

        }
        Char::class -> {

        }
        String::class -> {

        }
        else -> {
            throw NoPrimitiveTypeException(
                    "No such primitive type define, please contact authors type=${kclass.simpleName}")
        }
    }
}