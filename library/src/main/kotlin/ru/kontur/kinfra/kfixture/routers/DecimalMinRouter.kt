package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.decimal.min.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class DecimalMinRouter {

    fun <T : Any> process(param: T?, decimalMin: DecimalMin): Any {
        return when (param) {
            is BigDecimal? -> {
                MinBigDecimalGenerator().process(param, decimalMin)
            }
            is BigInteger? -> {
                MinBigIntegerGenerator().process(param, decimalMin)
            }
            is Byte? -> {
                MinByteGenerator().process(param, decimalMin)
            }
            is Short? -> {
                MinShortGenerator().process(param, decimalMin)
            }
            is Int? -> {
                MinIntGenerator().process(param, decimalMin)
            }
            is Long? -> {
                MinLongGenerator().process(param, decimalMin)
            }
            else -> {
                throw IllegalArgumentException("Can't find such case in decimalMin generation")
            }
        }
    }
}