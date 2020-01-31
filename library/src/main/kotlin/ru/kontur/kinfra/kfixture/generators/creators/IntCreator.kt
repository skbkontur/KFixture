package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator

class IntCreator : VariableCreator<Int> {
    override fun create(value: Long): Int {
        return value.toInt()
    }
}