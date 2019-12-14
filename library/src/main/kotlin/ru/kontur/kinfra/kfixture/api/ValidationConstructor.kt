package ru.kontur.kinfra.kfixture.api

interface ValidationConstructor<T : Any> {
    fun call(): T
}