package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinByteGenerator : ValidParamGenerator<Byte, DecimalMin> {
    override fun process(param: Byte?, annotation: DecimalMin): Byte {
        val min = annotation.value.toInt()
        return if (param == null || param < min) {
            (min + 1).toByte()
        } else {
            param
        }
    }
}