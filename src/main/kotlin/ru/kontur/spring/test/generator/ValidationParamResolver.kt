package ru.kontur.spring.test.generator

import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
interface ValidationParamResolver {

    fun <T> process(param: T, clazz: KClass<*>, type: KType): T
}