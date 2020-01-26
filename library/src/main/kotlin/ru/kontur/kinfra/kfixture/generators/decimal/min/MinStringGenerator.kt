package ru.kontur.kinfra.kfixture.generators.decimal.min

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.DecimalMin

class MinStringGenerator : ValidParamGenerator<String, DecimalMin> {
    override fun process(param: String?, annotation: DecimalMin): String {
        val min = annotation.value.toInt()
        return if (param == null || param.length < min) {
            generateString(min + 1)
        } else {
            param
        }
    }
}