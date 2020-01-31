package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.AssertTrue

class AssertTrueGenerator : ValidParamGenerator<Boolean, AssertTrue> {
    override fun process(param: Boolean?, annotation: AssertTrue): Boolean {
        return true
    }
}