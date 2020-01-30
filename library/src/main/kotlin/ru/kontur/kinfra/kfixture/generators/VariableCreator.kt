package ru.kontur.kinfra.kfixture.generators

interface VariableCreator<T> {
    fun create(value: Long): T
}