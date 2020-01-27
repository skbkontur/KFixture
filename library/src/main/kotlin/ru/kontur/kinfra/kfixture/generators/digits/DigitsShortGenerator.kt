package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits

class DigitsShortGenerator : ValidParamGenerator<Short, Digits> {
    override fun process(param: Short?, annotation: Digits): Short {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}