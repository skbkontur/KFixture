package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass
import kotlin.reflect.KType

class DecimalMinGenerator(
    private val min: String,
    private val inclusive: Boolean
) : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType): Any {
        val minDecimal = BigDecimal(min)
        val value = if (generatedParam != null) {
            BigDecimal(generatedParam.toString())
        } else {
            null
        }
        val result = if (value == null || value < minDecimal) {
            minDecimal + BigDecimal(1)
        } else {
            value
        }

        return when (clazz) {
            BigDecimal::class -> {
                result
            }
            BigInteger::class -> {
                result.toBigInteger()
            }
            Long::class -> {
                result.toLong()
            }
            Int::class -> {
                result.toInt()
            }
            Short::class -> {
                result.toShort()
            }
            Byte::class -> {
                result.toByte()
            }
            String::class -> {
                result.toString()
            }
            else -> value!!
        }
    }
}