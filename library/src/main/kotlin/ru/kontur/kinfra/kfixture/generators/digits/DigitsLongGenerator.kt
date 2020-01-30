package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits

class DigitsLongGenerator : ValidParamGenerator<Long, Digits> {
    override fun process(param: Long?, annotation: Digits): Long {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}