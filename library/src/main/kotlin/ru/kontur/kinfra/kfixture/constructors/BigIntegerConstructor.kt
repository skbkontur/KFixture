package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import java.math.BigInteger
import kotlin.random.Random

class BigIntegerConstructor : ParamConstructor<BigInteger> {
    override fun call(): BigInteger {
        val value = Random.nextLong()
        return BigInteger.valueOf(value)
    }
}