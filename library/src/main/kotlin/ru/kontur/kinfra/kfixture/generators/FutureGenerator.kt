package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Future

class FutureGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, Future> {

    override fun process(param: T, annotation: Future): T? {
        if (param <= creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.FUTURE)
        }
        return param
    }
}