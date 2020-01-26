package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMax

class MaxLongGenerator : ValidParamGenerator<Long, DecimalMax> {
    override fun process(param: Long?, annotation: DecimalMax): Long {
        val max = annotation.value.toLong()
        return if (param == null || param > max) {
            max - 1
        } else {
            param
        }
    }
}