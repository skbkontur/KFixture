package ru.kontur.kinfra.kfixture.generators.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigDecimal
import javax.validation.constraints.Max

class MaxBigDecimalGenerator : ValidParamGenerator<BigDecimal, Max> {
    override fun process(param: BigDecimal?, annotation: Max): BigDecimal {
        val max = annotation.value
        if (param == null || param > BigDecimal(max)) {
            return BigDecimal(max - 1)
        }
        return param
    }
}