package ru.kontur.kinfra.kfixture.generators.max

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Max

class MaxByteGenerator : ValidParamGenerator<Byte, Max> {
    override fun process(param: Byte?, annotation: Max): Byte {
        val max = annotation.value
        if (param == null || param > max) {
            return (max - 1).toByte()
        }
        return param.toByte()
    }
}