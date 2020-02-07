package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator
import java.math.BigDecimal

class BigDecimalDigitsCreator : DigitsCreator<BigDecimal> {
    override fun create(integer: Int, fraction: Int): BigDecimal {
        return BigDecimal(DigitsUtils.createStr(integer, fraction))
    }
}