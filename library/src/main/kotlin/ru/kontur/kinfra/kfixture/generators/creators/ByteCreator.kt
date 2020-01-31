package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator

class ByteCreator : VariableCreator<Byte> {
    override fun create(value: Long): Byte {
        return value.toByte()
    }
}