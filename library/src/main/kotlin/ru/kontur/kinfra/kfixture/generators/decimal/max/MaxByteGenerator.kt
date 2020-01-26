package ru.kontur.kinfra.kfixture.generators.decimal.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.DecimalMax

class MaxByteGenerator : ValidParamGenerator<Byte, DecimalMax> {
    override fun process(param: Byte?, annotation: DecimalMax): Byte {
        val maxByte = annotation.value.toByte()
        return if (param == null || param > maxByte) {
            (maxByte - 1).toByte()
        } else {
            param
        }
    }
}