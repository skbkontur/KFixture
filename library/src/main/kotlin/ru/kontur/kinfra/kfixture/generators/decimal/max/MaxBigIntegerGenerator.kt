package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigInteger
import javax.validation.constraints.DecimalMax

class MaxBigIntegerGenerator : ValidParamGenerator<BigInteger, DecimalMax> {
    override fun process(param: BigInteger?, annotation: DecimalMax): BigInteger {
        val maxInteger = BigInteger(annotation.value)
        return if (param == null || param > maxInteger) {
            maxInteger - BigInteger.valueOf(1)
        } else {
            param
        }
    }
}