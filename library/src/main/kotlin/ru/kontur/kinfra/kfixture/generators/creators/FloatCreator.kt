package ru.kontur.kinfra.kfixture.generators.creators

import ru.kontur.kinfra.kfixture.generators.VariableCreator

class FloatCreator : VariableCreator<Float> {
    override fun create(value: Long): Float {
        return value.toFloat()
    }
}