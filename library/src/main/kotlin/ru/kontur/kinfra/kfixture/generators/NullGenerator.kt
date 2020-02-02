package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Null

class NullGenerator<T> : ValidParamGenerator<T, Null> {
    override fun process(param: T, annotation: Null): T? {
        return null
    }
}