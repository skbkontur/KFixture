package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.AssertFalse

class AssertFalseGenerator : ValidParamGenerator<Boolean, AssertFalse> {
    override fun process(param: Boolean, annotation: AssertFalse): Boolean? {
        return false
    }
}