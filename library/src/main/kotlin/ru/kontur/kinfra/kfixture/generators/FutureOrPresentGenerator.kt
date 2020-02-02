package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.generators.dates.DateCreator
import ru.kontur.kinfra.kfixture.generators.dates.TimeInterval
import javax.validation.constraints.FutureOrPresent
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FutureOrPresentGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, FutureOrPresent> {
    override fun process(
        param: T,
        annotation: FutureOrPresent,
        clazz: KClass<T>,
        type: KType
    ): T? {
        if (param < creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.FUTURE)
        }
        return param
    }
}