package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.NotNull

class NotNullGenerator<T> : ValidParamGenerator<T, NotNull> {
    override fun process(param: T, annotation: NotNull): T? {
        return param
    }
}