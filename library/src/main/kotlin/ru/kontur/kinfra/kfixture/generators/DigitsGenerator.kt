package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Digits
import kotlin.reflect.KClass
import kotlin.reflect.KType

class DigitsGenerator<T : Any>(
    private val creator: VariableCreator<T>
) : ValidParamGenerator<T, Digits> {

    override fun process(
        param: T,
        annotation: Digits,
        clazz: KClass<T>,
        type: KType
    ): T? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}