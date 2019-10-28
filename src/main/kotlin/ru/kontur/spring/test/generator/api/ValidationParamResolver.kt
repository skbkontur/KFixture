package ru.kontur.spring.test.generator.api

import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
interface ValidationParamResolver {

    fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any?
}