package ru.kontur.kinfra.kfixture.generators.decimal

import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinLongGenerator {
    fun process(param: Long?, decimalMin: DecimalMin): Long {
        val min = decimalMin.value.toInt()
        return if (param == null || param < min) {
            (min + 1).toLong()
        } else {
            param
        }
    }
}