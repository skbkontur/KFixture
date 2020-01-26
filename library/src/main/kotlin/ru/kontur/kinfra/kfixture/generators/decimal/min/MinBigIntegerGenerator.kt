package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigInteger
import javax.validation.constraints.DecimalMin

/**
 * @author KonstantinVolivach
 */
class MinBigIntegerGenerator : ValidParamGenerator<BigInteger, DecimalMin> {
    override fun process(param: BigInteger?, annotation: DecimalMin): BigInteger {
        val minInteger = BigInteger(annotation.value)
        return if (param == null || param < minInteger) {
            minInteger + BigInteger.valueOf(1)
        } else {
            param
        }
    }
}