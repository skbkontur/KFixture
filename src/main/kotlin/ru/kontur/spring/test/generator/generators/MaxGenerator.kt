package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ValidatorFor
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Max
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ValidatorFor(value = Max::class)
class MaxGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        val max = (annotation as Max).value
        when (clazz) {
            BigDecimal::class -> {
                if (generatedParam == null || generatedParam is BigDecimal && generatedParam > BigDecimal(max)) {
                    return BigDecimal(max - 1)
                }
            }
            BigInteger::class -> {
                if (generatedParam == null || generatedParam is BigInteger && generatedParam > BigInteger.valueOf(max)) {
                    return BigInteger.valueOf(max - 1)
                }
            }
            Byte::class -> {
                if (generatedParam == null || generatedParam is Byte && generatedParam > max) {
                    return (max - 1).toByte()
                }
            }
            Short::class -> {
                if (generatedParam == null || generatedParam is Short && generatedParam > max) {
                    return (max - 1).toShort()
                }
            }
            Int::class -> {
                if (generatedParam == null || generatedParam is Int && generatedParam > max) {
                    return (max - 1).toInt()
                }
            }
            Long::class -> {
                if (generatedParam == null || generatedParam is Long && generatedParam > max) {
                    return max - 1
                }
            }
        }
        return generatedParam
    }
}