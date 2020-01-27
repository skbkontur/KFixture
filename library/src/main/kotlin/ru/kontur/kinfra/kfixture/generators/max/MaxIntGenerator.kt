package ru.kontur.kinfra.kfixture.generators.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Max

class MaxIntGenerator : ValidParamGenerator<Int, Max> {
    override fun process(param: Int?, annotation: Max): Int {
        val max = annotation.value
        if (param == null || param > max) {
            return (max - 1).toInt()
        }
        return param
    }
}