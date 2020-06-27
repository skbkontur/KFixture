package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import java.math.BigDecimal
import kotlin.random.Random

class BigDecimalConstructor : ParamConstructor<BigDecimal> {
    override fun call(): BigDecimal {
        val value = Random.nextDouble()
        return BigDecimal(value)
    }
}