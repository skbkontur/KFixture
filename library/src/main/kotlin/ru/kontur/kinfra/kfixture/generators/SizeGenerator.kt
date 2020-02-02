package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Size

class SizeGenerator<T> : ValidParamGenerator<T, Size> {
    override fun process(param: T, annotation: Size): T? {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}