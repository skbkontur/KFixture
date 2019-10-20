package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class NegativeGenerator : ValidationParamResolver {
    private companion object {
        const val DEFAULT_MINUS = -1L
    }

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType): Any {
        if (generatedParam == null) {
            return DEFAULT_MINUS
        }
        when (clazz) {
            BigDecimal::class -> {
                if (generatedParam is BigDecimal && generatedParam < BigDecimal(0)) {
                    return BigDecimal(DEFAULT_MINUS)
                }
            }
            BigInteger::class -> {
                if (generatedParam is BigInteger && generatedParam < BigInteger.valueOf(0)) {
                    return BigInteger.valueOf(DEFAULT_MINUS)
                }
            }
            Byte::class -> {
                if (generatedParam is Byte && generatedParam < 0) {
                    return DEFAULT_MINUS.toByte()
                }
            }
            Short::class -> {
                if (generatedParam is Short && generatedParam < 0) {
                    return DEFAULT_MINUS.toShort()
                }
            }
            Int::class -> {
                if (generatedParam is Int && generatedParam < 0) {
                    return DEFAULT_MINUS.toInt()
                }
            }
            Long::class -> {
                if (generatedParam is Long && generatedParam < 0) {
                    return DEFAULT_MINUS
                }
            }
            Float::class -> {
                if (generatedParam is Float && generatedParam < 0) {
                    return DEFAULT_MINUS.toFloat()
                }
            }
            Double::class -> {
                if (generatedParam is Double && generatedParam < 0) {
                    return DEFAULT_MINUS.toDouble()
                }
            }
        }
        return generatedParam
    }
}