package ru.kontur.kinfra.kfixture.api

/**
 * Use to constructor difficult param
 * if kfixture can't construct them automatically
 * you can provide construction by self
 */
interface ParamConstructor<T : Any> {
    fun call(): T
}