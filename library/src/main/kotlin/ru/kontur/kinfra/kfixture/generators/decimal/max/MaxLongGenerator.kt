package ru.kontur.kinfra.kfixture.generators.decimal.max

import javax.validation.constraints.DecimalMax

class MaxLongGenerator {
    fun process(param: Long?, decimalMax: DecimalMax): Long {
        val max = decimalMax.value.toLong()
        return if (param == null || param > max) {
            max - 1
        } else {
            param
        }
    }
}