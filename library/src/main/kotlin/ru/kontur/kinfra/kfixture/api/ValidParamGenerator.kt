package ru.kontur.kinfra.kfixture.api

interface ValidParamGenerator<P, in A> {
    fun process(param: P?, annotation: A): P
}