package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigDecimal
import javax.validation.constraints.Digits

class DigitsBigDecimalGenerator : ValidParamGenerator<BigDecimal, Digits> {
    override fun process(param: BigDecimal?, annotation: Digits): BigDecimal {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}