package ru.kontur.kinfra.kfixture.routers

interface ValidRouter<T : Any, A> {
    fun process(param: T, annotation: A): Any?
}