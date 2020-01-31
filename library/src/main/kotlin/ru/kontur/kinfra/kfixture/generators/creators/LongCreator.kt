package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator

class LongCreator : VariableCreator<Long> {
    override fun create(value: Long): Long {
        return value
    }
}