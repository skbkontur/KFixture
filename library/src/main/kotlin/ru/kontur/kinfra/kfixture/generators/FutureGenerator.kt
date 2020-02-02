package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.generators.dates.DateCreator
import ru.kontur.kinfra.kfixture.generators.dates.TimeInterval
import javax.validation.constraints.Future
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FutureGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, Future> {
    override fun process(
        param: T,
        annotation: Future,
        clazz: KClass<T>,
        type: KType
    ): T? {
        if (param <= creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.FUTURE)
        }
        return param
    }
}