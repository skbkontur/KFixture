package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMax

class MaxIntGenerator : ValidParamGenerator<Int, DecimalMax> {
    override fun process(param: Int?, annotation: DecimalMax): Int {
        val max = annotation.value.toInt()
        return if (param == null || param > max) {
            max - 1
        } else {
            param
        }
    }
}