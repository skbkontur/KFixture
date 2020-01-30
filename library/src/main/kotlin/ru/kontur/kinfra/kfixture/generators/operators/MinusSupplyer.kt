package ru.kontur.kinfra.kfixture.generators.operators

interface MinusSupplyer<T> {
    fun minus(first: T, other: T): T
}