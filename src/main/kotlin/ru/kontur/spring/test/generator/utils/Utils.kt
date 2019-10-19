package ru.kontur.spring.test.generator.utils

import ru.kontur.spring.test.generator.exceptions.NoPrimitiveTypeException
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter

/**
 * @author Konstantin Volivach
 */

fun generateString(numOfElements: Int): String {
    return (1..numOfElements).map { generateRandomChar() }.joinToString(separator = "") { "$it" }
}

fun generateRandomChar(): Char {
    return Random.nextInt().toChar()
}

fun generateDouble(): Double {
    return Random.nextDouble()
}

fun generateMap(numOfElements: Int, classRef: KClass<*>, type: KType): Map<Any, Any> {
    val keyType = type.arguments[0].type!!
    val valueType = type.arguments[1].type!!
    val keys = (1..numOfElements).map { makeRandomInstanceForParam(keyType, classRef, type) }
    val values = (1..numOfElements).map { makeRandomInstanceForParam(valueType, classRef, type) }
    return keys.zip(values).toMap()
}

fun generateCollection(numOfElements: Int, classRef: KClass<*>, type: KType): Any {
    val elemType = type.arguments[0].type!!
    return (1..numOfElements).map { makeRandomInstanceForParam(elemType, classRef, type) }
}

fun makeRandomInstanceForParam(paramType: KType, classRef: KClass<*>, type: KType): Any {
    val classifier = paramType.classifier
    return when (classifier) {
        is KClass<*> -> {
            generatePrimitiveValue(classifier)
        }
        is KTypeParameter -> {
            val typeParamterName = classifier.name
            val typeParameterId = classRef.typeParameters.indexOfFirst { it.name == typeParamterName }
            val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
            generatePrimitiveValue(parameterType.classifier as KClass<*>)
        }
        else -> throw IllegalArgumentException("Type of classifier $classifier is not supported")
    }
}

fun makeRandomInstance(classRef: KClass<*>, type: KType): Any? {
    val primitive = generatePrimitiveValue(classRef)
    if (primitive != null) {
        return primitive
    }

    val constructors = classRef.constructors.shuffled(Random)

    for (constructor in constructors) {
        try {
            val arguments = constructor.parameters
                .map { makeRandomInstanceForParam(it.type, classRef, type) }
                .toTypedArray()

            return constructor.call(*arguments)
        } catch (e: Throwable) {
            e.printStackTrace()
            // no-op. We catch any possible error here that might occur during class creation
        }
    }

    throw IllegalArgumentException("No useable constructor")
}


fun generatePrimitiveValue(kclass: KClass<*>): Any {
    return when (kclass) {
        Double::class -> {
            Random.nextDouble()
        }
        Int::class -> {
            Random.nextInt()
        }
        Float::class -> {
            Random.nextFloat()
        }
        Char::class -> {
            generateRandomChar()
        }
        String::class -> {
            generateString(Random.nextInt(100))
        }
        else -> {
            throw NoPrimitiveTypeException(
                "No such primitive type define, please contact authors type=${kclass.simpleName}"
            )
        }
    }
}