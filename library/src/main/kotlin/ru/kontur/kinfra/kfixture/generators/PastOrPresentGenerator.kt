package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.PastOrPresent

class PastOrPresentGenerator<T> : ValidParamGenerator<T, PastOrPresent> {
    override fun process(param: T, annotation: PastOrPresent): T {
        TODO()
    }
}