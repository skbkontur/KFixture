package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits

class DigitsStringGenerator : ValidParamGenerator<String, Digits> {
    override fun process(param: String?, annotation: Digits): String {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}