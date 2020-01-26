package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMax

class MaxShortGenerator : ValidParamGenerator<Short, DecimalMax> {
    override fun process(param: Short?, annotation: DecimalMax): Short {
        val max = annotation.value.toShort()
        return if (param == null || param > max) {
            (max - 1).toShort()
        } else {
            param
        }
    }
}