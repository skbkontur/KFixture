package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.generators.dates.DateCreator
import ru.kontur.kinfra.kfixture.generators.dates.TimeInterval
import javax.validation.constraints.Past
import kotlin.reflect.KClass
import kotlin.reflect.KType

class PastGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, Past> {
    override fun process(
        param: T,
        annotation: Past,
        clazz: KClass<T>,
        type: KType
    ): T? {
        if (param >= creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.PAST)
        }
        return param
    }
}