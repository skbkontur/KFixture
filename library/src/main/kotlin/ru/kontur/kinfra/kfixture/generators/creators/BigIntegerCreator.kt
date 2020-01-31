package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator
import java.math.BigInteger

class BigIntegerCreator : VariableCreator<BigInteger> {
    override fun create(value: Long): BigInteger {
        return BigInteger.valueOf(value)
    }
}