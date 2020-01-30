package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMin

/**
 * @author Konstantin Volivach
 */
class MinLongGenerator : ValidParamGenerator<Long, DecimalMin> {
    override fun process(param: Long?, annotation: DecimalMin): Long {
        val min = annotation.value.toInt()
        return if (param == null || param < min) {
            (min + 1).toLong()
        } else {
            param
        }
    }
}