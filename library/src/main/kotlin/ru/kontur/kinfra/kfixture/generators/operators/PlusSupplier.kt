package ru.kontur.kinfra.kfixture.generators.operators

interface PlusSupplier<T> {
    fun plus(first: T, second: T): T
}