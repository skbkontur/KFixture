package ru.kontur.spring.test.generator.processor

import ru.kontur.spring.test.generator.utils.generateRandomChar
import ru.kontur.spring.test.generator.utils.generateString
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

abstract class AbstractGenerateProcessor : GenerateProcessor {

    protected fun generatePrimitiveValue(kclass: KClass<*>, type: KType?, annotationList: List<Annotation>?): Any? {
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
            Short::class -> {
                Random.nextInt().toShort()
            }
            Byte::class -> {
                Random.nextInt(256).toByte()
            }
            Char::class -> {
                generateRandomChar()
            }
            String::class -> {
                generateString(Random.nextInt(100))
            }
            List::class -> {
                generateCollection(10, kclass, type!!, annotationList)
            }
            Map::class -> {
                generateMap(10, kclass, type!!, annotationList)
            }
            else -> null
        }
    }

    private fun generateMap(
        numOfElements: Int,
        classRef: KClass<*>,
        type: KType,
        annotationList: List<Annotation>?
    ): Map<Any, Any> {
        val keyType = type.arguments[0].type!!
        val valueType = type.arguments[1].type!!
        val keys =
            (1..numOfElements).mapNotNull { generateParam(keyType.classifier as KClass<*>, keyType, annotationList) }
        val values =
            (1..numOfElements).mapNotNull {
                generateParam(
                    valueType.classifier as KClass<*>,
                    valueType,
                    annotationList
                )
            }
        return keys.zip(values).toMap()
    }

    private fun generateCollection(
        numOfElements: Int,
        classRef: KClass<*>,
        type: KType,
        annotationList: List<Annotation>?
    ): Any {
        val elemType = type.arguments[0].type!!
        return (1..numOfElements).map { generateParam(elemType.classifier as KClass<*>, elemType, annotationList) }
    }
}