package ru.kontur.kinfra.kfixture.generators.decimal.min

import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinByteGenerator {
    fun process(param: Byte?, decimalMin: DecimalMin): Byte {
        val min = decimalMin.value.toInt()
        return if (param == null || param < min) {
            (min + 1).toByte()
        } else {
            param
        }
    }
}