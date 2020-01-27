package ru.kontur.kinfra.kfixture.generators.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Max

class MaxShortGenerator : ValidParamGenerator<Short, Max> {
    override fun process(param: Short?, annotation: Max): Short {
        val max = annotation.value
        if (param == null || param > max) {
            return (max - 1).toShort()
        }
        return param
    }
}