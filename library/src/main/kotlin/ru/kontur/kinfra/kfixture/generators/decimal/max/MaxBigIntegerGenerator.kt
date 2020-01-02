package ru.kontur.kinfra.kfixture.generators.decimal.max

import java.math.BigInteger
import javax.validation.constraints.DecimalMax

class MaxBigIntegerGenerator {
    fun process(param: BigInteger?, decimalMax: DecimalMax): BigInteger {
        val maxInteger = BigInteger(decimalMax.value)
        return if (param == null || param > maxInteger) {
            maxInteger - BigInteger.valueOf(1)
        } else {
            param
        }
    }
}