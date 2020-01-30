package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin

class MinBigDecimalGenerator : ValidParamGenerator<BigDecimal, DecimalMin> {
    override fun process(param: BigDecimal?, annotation: DecimalMin): BigDecimal {
        val minDecimal = BigDecimal(annotation.value)
        return if (param == null || param < minDecimal) {
            minDecimal + BigDecimal(1)
        } else {
            param
        }
    }
}