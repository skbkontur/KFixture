package ru.kontur.spring.test.generator.api

interface ValidationConstructor<T : Any> {
    fun call(): T
}