package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.DecimalMax
import kotlin.reflect.KClass
import kotlin.reflect.KType

class DecimalMaxGenerator : ValidationParamResolver {

    // TODO think about template
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any {
        val decimalMax = annotation as DecimalMax
        val maxDecimal = BigDecimal(decimalMax.value)
        val value = if (generatedParam == null) {
            null
        } else {
            BigDecimal(generatedParam.toString())
        }
        val result = if (value == null || value > maxDecimal) {
            maxDecimal - BigDecimal(1)
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