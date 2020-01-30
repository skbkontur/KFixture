package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.DecimalMax

class MaxStringGenerator : ValidParamGenerator<String, DecimalMax> {
    override fun process(param: String?, annotation: DecimalMax): String {
        val max = annotation.value.toInt()

        return if (param == null || param.length > max) {
            generateString(max - 1)
        } else {
            param
        }
    }
}