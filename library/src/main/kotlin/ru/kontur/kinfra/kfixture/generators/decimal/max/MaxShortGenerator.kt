package ru.kontur.kinfra.kfixture.generators.decimal.max

import javax.validation.constraints.DecimalMax

class MaxShortGenerator {
    fun process(param: Short?, decimalMax: DecimalMax): Short {
        val max = decimalMax.value.toShort()
        return if (param == null || param > max) {
            (max - 1).toShort()
        } else {
            param
        }
    }
}