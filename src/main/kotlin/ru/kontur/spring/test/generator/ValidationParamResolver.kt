package ru.kontur.spring.test.generator

/**
 * @author Konstantin Volivach
 */
interface ValidationParamResolver<T> {

    fun process(param: T): T
}