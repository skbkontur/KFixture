package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class MinGenerator(private val min: Long) : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType): Any {
        when (clazz) {
            BigDecimal::class -> {
                if (generatedParam == null || generatedParam is BigDecimal && generatedParam < BigDecimal(min)) {
                    return BigDecimal(min)
                }
            }
            BigInteger::class -> {
                if (generatedParam == null || generatedParam is BigInteger && generatedParam < BigInteger.valueOf(min)) {
                    return BigInteger.valueOf(min)
                }
            }
            Byte::class -> {
                if (generatedParam == null || generatedParam is Byte && generatedParam < min) {
                    return min.toByte()
                }
            }
            Short::class -> {
                if (generatedParam == null || generatedParam is Short && generatedParam < min) {
                    return min.toShort()
                }
            }
            Int::class -> {
                if (generatedParam == null || generatedParam is Int && generatedParam < min) {
                    return min.toInt()
                }
            }
            Long::class -> {
                if (generatedParam == null || generatedParam is Long && generatedParam < min) {
                    return min
                }
            }
        }
        return generatedParam!!
    }
}