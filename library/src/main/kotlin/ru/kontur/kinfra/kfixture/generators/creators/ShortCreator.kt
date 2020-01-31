package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator

class ShortCreator : VariableCreator<Short> {
    override fun create(value: Long): Short {
        return value.toShort()
    }
}