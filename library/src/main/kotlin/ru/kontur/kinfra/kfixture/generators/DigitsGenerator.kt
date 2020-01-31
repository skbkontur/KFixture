package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits

class DigitsGenerator<T>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, Digits> {

    override fun process(param: T, annotation: Digits): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}