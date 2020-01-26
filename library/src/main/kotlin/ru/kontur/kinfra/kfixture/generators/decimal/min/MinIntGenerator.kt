package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinIntGenerator : ValidParamGenerator<Int, DecimalMin> {
    override fun process(param: Int?, annotation: DecimalMin): Int {
        val min = annotation.value.toInt()
        return if (param == null || param < min) {
            min + 1
        } else {
            param
        }
    }
}