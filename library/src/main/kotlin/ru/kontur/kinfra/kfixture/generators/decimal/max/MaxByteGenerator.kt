package ru.kontur.kinfra.kfixture.generators.decimal.max

import javax.validation.constraints.DecimalMax

class MaxByteGenerator {
    fun process(param: Byte?, decimalMax: DecimalMax): Byte {
        val maxByte = decimalMax.value.toByte()
        return if (param == null || param > maxByte) {
            (maxByte - 1).toByte()
        } else {
            param
        }
    }
}