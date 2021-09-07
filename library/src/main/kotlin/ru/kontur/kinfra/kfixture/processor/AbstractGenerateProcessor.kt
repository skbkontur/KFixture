package ru.kontur.kinfra.kfixture.processor

import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.utils.generateRandomChar
import ru.kontur.kinfra.kfixture.utils.generateString
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

abstract class AbstractGenerateProcessor : GenerateProcessor {
    abstract val collectionSettings: CollectionSettings

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
                generateString(10)
            }
            kclass == List::class -> {
                val collectionType = requireNotNull(type) {
                    "collectionType must be not null"
                }
                generateCollection(collectionSettings.size, collectionType, annotationList)
            }
            kclass == Map::class -> {
                val mapType = requireNotNull(type) {
                    "mapType must not be null"
                }
                generateMap(collectionSettings.size, mapType, annotationList)
            }
            kclass == Set::class -> {
                val setType = requireNotNull(type) {
                    "setType must not be null"
                }
                generateSet(collectionSettings.size, setType, annotationList)
            }
            kclass == Boolean::class -> {
                Random.nextBoolean()
            }
            kclass == ByteArray::class -> {
                ByteArray(collectionSettings.size)
            }
            kclass == CharArray::class -> {
                CharArray(collectionSettings.size)
            }
            kclass == IntArray::class -> {
                IntArray(collectionSettings.size)
            }
            kclass == DoubleArray::class -> {
                DoubleArray(collectionSettings.size)
            }
            kclass == LongArray::class -> {
                LongArray(collectionSettings.size)
            }
            kclass == FloatArray::class -> {
                FloatArray(collectionSettings.size)
            }
            kclass == ShortArray::class -> {
                ShortArray(collectionSettings.size)
            }
            kclass.simpleName == "Array" -> {
                val arrayType = requireNotNull(type) {
                    "arrayType must not be null"
                }
                generateCollection(collectionSettings.size, arrayType, annotationList).toTypedArray()
            }
            else -> null
        }
    }

    private fun generateMap(
        numOfElements: Int,
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
        type: KType,
        annotationList: List<Annotation>?
    ): List<Any?> {
        val elemType = type.arguments[0].type!!
        return (1..numOfElements).map { generateParam(elemType.classifier as KClass<*>, elemType, annotationList) }
    }

    private fun generateSet(
        numOfElements: Int,
        type: KType,
        annotationList: List<Annotation>?
    ): Set<Any?> {
        return generateCollection(numOfElements, type, annotationList).toSet()
    }
}