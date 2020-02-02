package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator
import java.math.BigDecimal

class BigDecimalCreator : VariableCreator<BigDecimal> {
    override fun create(value: Long): BigDecimal {
        return BigDecimal(value)
    }
}