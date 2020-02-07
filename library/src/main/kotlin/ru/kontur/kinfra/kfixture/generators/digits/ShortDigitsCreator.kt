package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator

class ShortDigitsCreator : DigitsCreator<Short> {
    override fun create(integer: Int, fraction: Int): Short {
        return DigitsUtils.createStr(integer, fraction).toShort()
    }
}