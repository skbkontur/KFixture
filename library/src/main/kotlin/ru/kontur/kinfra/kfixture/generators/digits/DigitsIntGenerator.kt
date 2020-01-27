package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits

class DigitsIntGenerator : ValidParamGenerator<Int, Digits> {
    override fun process(param: Int?, annotation: Digits): Int {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}