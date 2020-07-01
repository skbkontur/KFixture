package ru.kontur.kinfra.kfixture.api

/**
 * it is used to generate validation data and ignore generation's algorithm
 */
interface ValidationConstructor<T : Any> {
    fun call(): T
}