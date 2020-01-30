package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigDecimal
import javax.validation.constraints.DecimalMax

class MaxBigDecimalGenerator : ValidParamGenerator<BigDecimal, DecimalMax> {
    override fun process(param: BigDecimal?, annotation: DecimalMax): BigDecimal {
        val maxDecimal = BigDecimal(annotation.value)
        return if (param == null || param > maxDecimal) {
            maxDecimal - BigDecimal(1)
        } else {
            param
        }
    }
}