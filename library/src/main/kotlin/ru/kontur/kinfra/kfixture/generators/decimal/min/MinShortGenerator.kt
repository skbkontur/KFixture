package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinShortGenerator : ValidParamGenerator<Short, DecimalMin> {
    override fun process(param: Short?, annotation: DecimalMin): Short {
        val min = annotation.value.toInt()
        return if (param == null || param < min) {
            (min + 1).toShort()
        } else {
            param
        }
    }
}