package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.DecimalMin

class MinStringGenerator {
    fun process(param: String?, decimalMin: DecimalMin): String {
        val min = decimalMin.value.toInt()
        return if (param == null || param.length < min) {
            generateString(min + 1)
        } else {
            param
        }
    }
}