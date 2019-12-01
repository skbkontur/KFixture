package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.NegativeOrZero
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = NegativeOrZero::class)
class NegativeOrZeroGenerator : ValidationParamResolver {
    companion object {
        const val DEFAULT_VALUE = 0L
    }

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        if (generatedParam == null) {
            return DEFAULT_VALUE
        }

        when (clazz) {
            BigDecimal::class -> {
                if (generatedParam is BigDecimal && generatedParam.toLong() > 0L) {
                    return BigDecimal(DEFAULT_VALUE)
                }
            }
            BigInteger::class -> {
                if (generatedParam is BigInteger && generatedParam.toLong() > 0L) {
                    return BigInteger.valueOf(DEFAULT_VALUE)
                }
            }
            Byte::class -> {
                if (generatedParam is Byte && generatedParam > 0) {
                    return DEFAULT_VALUE.toByte()
                }
            }
            Short::class -> {
                if (generatedParam is Short && generatedParam.toShort() > 0) {
                    return DEFAULT_VALUE.toShort()
                }
            }
            Long::class -> {
                if (generatedParam is Long && generatedParam.toLong() > 0L) {
                    return DEFAULT_VALUE
                }
            }
            Float::class -> {
                if (generatedParam is Float && generatedParam.toFloat() > 0f) {
                    return DEFAULT_VALUE.toFloat()
                }
            }
            Double::class -> {
                if (generatedParam is Double && generatedParam.toDouble() > 0.0) {
                    return DEFAULT_VALUE.toDouble()
                }
            }
        }

        return generatedParam
    }
}