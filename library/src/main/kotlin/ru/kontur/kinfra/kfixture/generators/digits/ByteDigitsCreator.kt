package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator

class ByteDigitsCreator : DigitsCreator<Byte> {
    override fun create(integer: Int, fraction: Int): Byte {
        return DigitsUtils.createStr(integer, fraction).toByte()
    }
}