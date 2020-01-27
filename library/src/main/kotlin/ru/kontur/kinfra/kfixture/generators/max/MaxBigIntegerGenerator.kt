package ru.kontur.kinfra.kfixture.generators.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigInteger
import javax.validation.constraints.Max

class MaxBigIntegerGenerator : ValidParamGenerator<BigInteger, Max> {
    override fun process(param: BigInteger?, annotation: Max): BigInteger {
        val max = annotation.value
        if (param == null || param > BigInteger.valueOf(max)) {
            return BigInteger.valueOf(max - 1)
        }
        return param
    }
}