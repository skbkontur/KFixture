package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.FutureOrPresent

class FutureOrPresentGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, FutureOrPresent> {
    override fun process(param: T, annotation: FutureOrPresent): T? {
        if (param < creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.FUTURE)
        }
        return param
    }
}