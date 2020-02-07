package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator

class LongDigitsCreator : DigitsCreator<Long> {
    override fun create(integer: Int, fraction: Int): Long {
        return DigitsUtils.createStr(integer, fraction).toLong()
    }
}