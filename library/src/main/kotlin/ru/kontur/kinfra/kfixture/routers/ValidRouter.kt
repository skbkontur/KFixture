package ru.kontur.kinfra.kfixture.routers

import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * Router is used to provide generation algorithm for your valid annotations
 */
interface ValidRouter<T : Any, A> {
    fun process(param: T, annotation: A, clazz: KClass<*>, type: KType): Any?
}