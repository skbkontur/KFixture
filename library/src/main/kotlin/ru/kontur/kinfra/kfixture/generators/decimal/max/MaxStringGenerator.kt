package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.DecimalMax

class MaxStringGenerator {
    fun process(param: String?, decimalMax: DecimalMax): String {
        val max = decimalMax.value.toInt()

        return if (param == null || param.length > max) {
            generateString(max - 1)
        } else {
            param
        }
    }
}