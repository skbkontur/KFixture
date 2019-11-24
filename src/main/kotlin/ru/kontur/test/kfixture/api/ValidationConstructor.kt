package ru.kontur.test.kfixture.api

interface ValidationConstructor<T : Any> {
    fun call(): T
}