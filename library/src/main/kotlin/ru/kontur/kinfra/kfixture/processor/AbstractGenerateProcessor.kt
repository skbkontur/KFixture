package ru.kontur.kinfra.kfixture.processor

import ru.kontur.kinfra.kfixture.utils.generateRandomChar
import ru.kontur.kinfra.kfixture.utils.generateString
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

abstract class AbstractGenerateProcessor : GenerateProcessor {

    protected fun generatePrimitiveValue(kclass: KClass<*>, type: KType?, annotationList: List<Annotation>?): Any? {
        return when {
            kclass == Double::class -> {
                Random.nextDouble()
            }
            kclass == Int::class -> {
                Random.nextInt()
            }
            kclass == Long::class -> {
                Random.nextLong()
            }
            kclass == Float::class -> {
                Random.nextFloat()
            }
            kclass == Short::class -> {
                Random.nextInt().toShort()
            }
            kclass == Byte::class -> {
                Random.nextInt(256).toByte()
            }
            kclass == Char::class -> {
                generateRandomChar()
            }
            kclass == String::class -> {
                generateString(Random.nextInt(100))
            }
            kclass == List::class -> {
                generateCollection(10, kclass, type!!, annotationList)
            }
            kclass == Map::class -> {
                generateMap(10, kclass, type!!, annotationList)
            }
            kclass == Set::class -> {
                generateSet(10, kclass, type!!, annotationList)
            }
            kclass == Boolean::class -> {
                Random.nextBoolean()
            }
            kclass == ByteArray::class -> {
                ByteArray(10)
            }
            kclass == CharArray::class -> {
                CharArray(10)
            }
            kclass == IntArray::class -> {
                IntArray(10)
            }
            kclass == DoubleArray::class -> {
                DoubleArray(10)
            }
            kclass == LongArray::class -> {
                LongArray(10)
            }
            kclass == FloatArray::class -> {
                FloatArray(10)
            }
            kclass == ShortArray::class -> {
                ShortArray(10)
            }
            kclass.simpleName == "Array" -> {
                val array = generateArray(10, kclass, type!!, annotationList)
                array
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

    private fun generateSet(
        numOfElements: Int,
        classRef: KClass<*>,
        type: KType,
        annotationList: List<Annotation>?
    ): Any {
        val elemType = type.arguments[0].type!!
        return (1..numOfElements).map { generateParam(elemType.classifier as KClass<*>, elemType, annotationList) }
            .toSet()
    }

    private fun generateArray(
        numOfElements: Int,
        classRef: KClass<*>,
        type: KType,
        annotationList: List<Annotation>?
    ): Any {
        val elemType = type.arguments[0].type!!
        return (1..numOfElements).map { generateParam(elemType.classifier as KClass<*>, elemType, annotationList) }
            .toTypedArray()
    }
}