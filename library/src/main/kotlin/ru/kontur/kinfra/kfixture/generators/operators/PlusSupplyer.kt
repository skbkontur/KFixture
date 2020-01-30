package ru.kontur.kinfra.kfixture.generators.operators

interface PlusSupplyer<T> {
    fun plus(first: T, second: T): T
}