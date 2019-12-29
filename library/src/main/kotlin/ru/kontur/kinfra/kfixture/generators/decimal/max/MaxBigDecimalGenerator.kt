package ru.kontur.kinfra.kfixture.generators.decimal.max

import java.math.BigDecimal
import javax.validation.constraints.DecimalMax

class MaxBigDecimalGenerator {
    fun process(param: BigDecimal?, decimalMax: DecimalMax): BigDecimal {
        val maxDecimal = BigDecimal(decimalMax.value)
        return if (param == null || param > maxDecimal) {
            maxDecimal - BigDecimal(1)
        } else {
            param
        }
    }
}