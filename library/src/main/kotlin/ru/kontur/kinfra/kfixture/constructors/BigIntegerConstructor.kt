package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.context.FixtureContext
import java.math.BigInteger
import kotlin.random.Random

class BigIntegerConstructor : ParamConstructor<BigInteger> {
    override fun call(context: FixtureContext): BigInteger {
        val value = Random.nextLong()
        return BigInteger.valueOf(value)
    }
}