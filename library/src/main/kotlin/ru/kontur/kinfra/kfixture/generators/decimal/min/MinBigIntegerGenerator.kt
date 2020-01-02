package ru.kontur.kinfra.kfixture.generators.decimal.min

import java.math.BigInteger
import javax.validation.constraints.DecimalMin

/**
 * @author KonstantinVolivach
 */
class MinBigIntegerGenerator {
    fun process(param: BigInteger?, decimalMin: DecimalMin): BigInteger {
        val minInteger = BigInteger(decimalMin.value)
        return if (param == null || param < minInteger) {
            minInteger + BigInteger.valueOf(1)
        } else {
            param
        }
    }
}