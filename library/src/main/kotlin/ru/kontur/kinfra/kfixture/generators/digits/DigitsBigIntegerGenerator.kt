package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import java.math.BigInteger
import javax.validation.constraints.Digits

class DigitsBigIntegerGenerator : ValidParamGenerator<BigInteger, Digits> {
    override fun process(param: BigInteger?, annotation: Digits): BigInteger {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}