package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.DecimalMin
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = DecimalMin::class)
class DecimalMinGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        val decimalMin = annotation as DecimalMin
        val minDecimal = BigDecimal(decimalMin.value)
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
            else -> value
        }
    }
}