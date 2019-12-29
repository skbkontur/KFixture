package ru.kontur.kinfra.kfixture.generators.decimal.min

import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinIntGenerator {
    fun process(param: Int?, decimalMin: DecimalMin): Int {
        val min = decimalMin.value.toInt()
        return if (param == null || param < min) {
            min + 1
        } else {
            param
        }
    }
}