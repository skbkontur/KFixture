package ru.kontur.kinfra.kfixture.generators.decimal.min

import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinShortGenerator {
    fun process(param: Short?, decimalMin: DecimalMin): Short {
        val min = decimalMin.value.toInt()
        return if (param == null || param < min) {
            (min + 1).toShort()
        } else {
            param
        }
    }
}