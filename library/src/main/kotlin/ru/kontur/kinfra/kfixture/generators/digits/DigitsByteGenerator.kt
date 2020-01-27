package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits

class DigitsByteGenerator : ValidParamGenerator<Byte, Digits> {

    override fun process(param: Byte?, annotation: Digits): Byte {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}