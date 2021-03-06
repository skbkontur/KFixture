package ru.kontur.kinfra.kfixture.api

import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@Deprecated(
    "Use ru.kontur.kinfra.kfixture.routers.ValidRouter instead",
    ReplaceWith("ValidRouter", "ru.kontur.kinfra.kfixture.routers.ValidRouter")
)
interface ValidationParamResolver {
    fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any?
}