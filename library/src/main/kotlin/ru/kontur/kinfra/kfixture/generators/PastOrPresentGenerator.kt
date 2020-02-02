package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.PastOrPresent
import kotlin.reflect.KClass
import kotlin.reflect.KType

class PastOrPresentGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, PastOrPresent> {
    override fun process(
        param: T,
        annotation: PastOrPresent,
        clazz: KClass<T>,
        type: KType
    ): T? {
        if (param > creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.PAST)
        }
        return param
    }
}