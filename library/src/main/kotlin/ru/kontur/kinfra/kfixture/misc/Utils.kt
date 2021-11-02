package ru.kontur.kinfra.kfixture.misc

import java.lang.RuntimeException
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter

/**
 * @author Konstantin Volivach
 */

const val A_LETTER_CODE = 97

fun generateString(numOfElements: Int): String {
    val builder = StringBuilder(numOfElements)
    for (i in 0 until numOfElements) {
        builder.appendCodePoint(generateRandomChar())
    }
    return builder.toString()
}

fun generateRandomChar(): Int {
    return Random.nextInt(26) + A_LETTER_CODE
}

fun generateMap(numOfElements: Int, classRef: KClass<*>, type: KType): Map<Any, Any> {
    val keyType = type.arguments[0].type!!
    val valueType = type.arguments[1].type!!
    val keys = (1..numOfElements).map { generateRandomInstanceForParam(keyType, classRef, type) }
    val values = (1..numOfElements).map { generateRandomInstanceForParam(valueType, classRef, type) }
    return keys.zip(values).toMap()
}

fun generateCollection(numOfElements: Int, classRef: KClass<*>, type: KType): Any {
    val elemType = type.arguments[0].type!!
    return (1..numOfElements).map { generateRandomInstanceForParam(elemType, classRef, type) }
}

fun generateRandomInstanceForParam(paramType: KType, classRef: KClass<*>, type: KType): Any {
    val classifier = paramType.classifier

    if ((classifier as? KClass<*>) != null && classifier.java.isEnum) {
        return generateEnum(classifier)
    }

    return when (classifier) {
        is KClass<*> -> {
            generateRandomInstance(classifier, type) ?: throw RuntimeException("Not generated")
        }
        is KTypeParameter -> {
            val typeParamterName = classifier.name
            val typeParameterId = classRef.typeParameters.indexOfFirst { it.name == typeParamterName }
            val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
            generatePrimitiveValue(parameterType.classifier as KClass<*>, type)
                ?: throw RuntimeException("Not generated")
        }
        else -> throw IllegalArgumentException("Type of classifier $classifier is not supported")
    }
}

fun generateEnum(clazz: KClass<*>): Any {
    val x = Random.nextInt(clazz.java.enumConstants.size)
    return clazz.java.enumConstants[x]
}

fun generateRandomInstance(classRef: KClass<*>, type: KType): Any? {
    val primitive = generatePrimitiveValue(classRef, type)
    if (primitive != null) {
        return primitive
    }

    val constructors = classRef.constructors.shuffled(Random)

    for (constructor in constructors) {
        try {
            val arguments = constructor.parameters
                .map { generateRandomInstanceForParam(it.type, classRef, type) }
                .toTypedArray()

            return constructor.call(*arguments)
        } catch (e: Throwable) {
            e.printStackTrace()
            // no-op. We catch any possible error here that might occur during class creation
        }
    }

    throw IllegalArgumentException("No useable constructor")
}

fun generatePrimitiveValue(kclass: KClass<*>, type: KType?): Any? {
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
            generateString(10)
        }
        List::class -> {
            generateCollection(10, kclass, type!!)
        }
        Map::class -> {
            generateMap(10, kclass, type!!)
        }
        else -> null
    }
}
