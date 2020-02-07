package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator

class IntDigitsCreator : DigitsCreator<Int> {
    override fun create(integer: Int, fraction: Int): Int {
        return DigitsUtils.createStr(integer, fraction).toInt()
    }
}