package ru.kontur.kinfra.kfixture.generators.decimal

import java.math.BigInteger
import javax.validation.constraints.DecimalMin

/**
 * @author KonstantinVolivach
 */
class MinBigIntegerGenerator {
    fun <T : BigInteger> process(param: T?, decimalMin: DecimalMin): BigInteger {
        val minInteger = BigInteger(decimalMin.value)

        return if (param == null || param < minInteger) {
            minInteger + BigInteger.valueOf(1)
        } else {
            param
        }
    }
}