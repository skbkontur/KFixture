package ru.kontur.kinfra.kfixture.generators.decimal.min

import java.math.BigDecimal
import javax.validation.constraints.DecimalMin

class MinBigDecimalGenerator {
    fun process(param: BigDecimal?, decimalMin: DecimalMin): BigDecimal {
        val minDecimal = BigDecimal(decimalMin.value)
        return if (param == null || param < minDecimal) {
            minDecimal + BigDecimal(1)
        } else {
            param
        }
    }
}