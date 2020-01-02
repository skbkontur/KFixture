package ru.kontur.kinfra.kfixture.generators.decimal.max

import javax.validation.constraints.DecimalMax

class MaxIntGenerator {
    fun process(param: Int?, decimalMax: DecimalMax): Int {
        val max = decimalMax.value.toInt()
        return if (param == null || param > max) {
            max - 1
        } else {
            param
        }
    }
}