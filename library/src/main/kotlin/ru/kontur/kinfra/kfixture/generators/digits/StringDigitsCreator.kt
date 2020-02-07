package ru.kontur.kinfra.kfixture.generators.digits

import ru.kontur.kinfra.kfixture.generators.DigitsCreator

class StringDigitsCreator : DigitsCreator<String> {
    override fun create(integer: Int, fraction: Int): String {
        return DigitsUtils.createStr(integer, fraction)
    }
}