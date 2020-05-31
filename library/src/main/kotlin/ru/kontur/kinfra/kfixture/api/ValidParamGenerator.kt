package ru.kontur.kinfra.kfixture.api

import kotlin.reflect.KClass
import kotlin.reflect.KType

interface ValidParamGenerator<P : Any, in A> {
    fun process(param: P, annotation: A, clazz: KClass<*>, type: KType): P?
}