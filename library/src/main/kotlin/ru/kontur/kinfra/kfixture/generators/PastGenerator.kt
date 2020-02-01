package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Past

class PastGenerator<T> : ValidParamGenerator<T, Past> {
    override fun process(param: T, annotation: Past): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}