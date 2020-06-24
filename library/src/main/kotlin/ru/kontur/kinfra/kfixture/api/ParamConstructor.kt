package ru.kontur.kinfra.kfixture.api

interface ParamConstructor<T : Any> {
    fun call(): T
}