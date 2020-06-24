package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import java.math.BigInteger
import kotlin.random.Random

class BigIntegerConstructor : ValidationConstructor<BigInteger> {
    override fun call(): BigInteger {
        val value = Random.nextLong()
        return BigInteger.valueOf(value)
    }
}