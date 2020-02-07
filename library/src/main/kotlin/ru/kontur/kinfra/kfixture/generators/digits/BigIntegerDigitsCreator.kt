package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator
import java.math.BigInteger

class BigIntegerDigitsCreator : DigitsCreator<BigInteger> {
    override fun create(integer: Int, fraction: Int): BigInteger {
        return BigInteger(DigitsUtils.createStr(integer, fraction))
    }
}