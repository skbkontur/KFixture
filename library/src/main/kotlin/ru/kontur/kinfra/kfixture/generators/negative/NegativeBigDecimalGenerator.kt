package ru.kontur.kinfra.kfixture.generators.negative

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigDecimal
import javax.validation.constraints.Negative

class NegativeBigDecimalGenerator : ValidParamGenerator<BigDecimal, Negative> {
    override fun process(param: BigDecimal?, annotation: Negative): BigDecimal {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}