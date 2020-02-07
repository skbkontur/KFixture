package ru.kontur.kinfra.kfixture.routers

import kotlin.reflect.KClass
import kotlin.reflect.KType

interface ValidRouter<T : Any, A> {
    fun process(param: T, annotation: A, clazz: KClass<T>, type: KType): Any?
}