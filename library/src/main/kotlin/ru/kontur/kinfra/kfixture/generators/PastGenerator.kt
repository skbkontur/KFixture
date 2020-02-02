package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Past

class PastGenerator<T : Comparable<T>>(
    private val creator: DateCreator<T>
) : ValidParamGenerator<T, Past> {
    override fun process(param: T, annotation: Past): T? {
        if (param >= creator.create(TimeInterval.NOW)) {
            return creator.create(TimeInterval.PAST)
        }
        return param
    }
}