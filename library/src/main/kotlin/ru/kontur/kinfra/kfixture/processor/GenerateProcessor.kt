package ru.kontur.kinfra.kfixture.processor

import kotlin.reflect.KClass
import kotlin.reflect.KType

interface GenerateProcessor {
    fun generateParam(clazz: KClass<*>, type: KType, annotation: List<Annotation>?): Any?
}