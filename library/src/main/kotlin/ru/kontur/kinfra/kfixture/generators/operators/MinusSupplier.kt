package ru.kontur.kinfra.kfixture.generators.operators

interface MinusSupplier<T> {
    fun minus(first: T, other: T): T
}