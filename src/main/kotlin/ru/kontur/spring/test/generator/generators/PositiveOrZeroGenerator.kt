package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ResolverFor
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.PositiveOrZero
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = PositiveOrZero::class)
class PositiveOrZeroGenerator : ValidationParamResolver {
    companion object {
        private const val DEFAULT_VALUE = 10
    }

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        val value = if (generatedParam != null && generatedParam is Number && generatedParam.toDouble() > 0) {
            generatedParam
        } else {
            BigDecimal(DEFAULT_VALUE)
        }
        when (clazz) {
            BigDecimal::class -> {
                return value
            }
            BigInteger::class -> {
                return BigInteger.valueOf(value.toLong())
            }
            Byte::class -> {
                return value.toByte()
            }
            Short::class -> {
                return value.toShort()
            }
            Int::class -> {
                return value.toInt()
            }
            Long::class -> {
                return value.toLong()
            }
            Float::class -> {
                return value.toFloat()
            }
            Double::class -> {
                return value.toDouble()
            }
        }
        return generatedParam
    }
}