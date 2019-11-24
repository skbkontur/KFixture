package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Negative
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ResolverFor(value = Negative::class)
class NegativeGenerator : ValidationParamResolver {
    private companion object {
        const val DEFAULT_MINUS = -1L
    }

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
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