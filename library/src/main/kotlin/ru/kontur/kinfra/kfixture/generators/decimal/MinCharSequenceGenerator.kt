package ru.kontur.kinfra.kfixture.generators.decimal

import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.DecimalMin

class MinCharSequenceGenerator {
    fun <T : CharSequence> process(param: T?, decimalMin: DecimalMin): CharSequence {
        val min = decimalMin.value.toInt()
        return if (param == null || param.length < min) {
            generateString(min + 1)
        } else {
            param
        }
    }
}