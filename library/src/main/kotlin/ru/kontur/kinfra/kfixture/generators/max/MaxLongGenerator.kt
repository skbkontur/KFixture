package ru.kontur.kinfra.kfixture.generators.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Max

class MaxLongGenerator : ValidParamGenerator<Long, Max> {
    override fun process(param: Long?, annotation: Max): Long {
        val max = annotation.value
        if (param == null || param > max) {
            return (max - 1)
        }
        return param
    }
}