package ru.kontur.kinfra.kfixture.api

import kotlin.reflect.KClass

/**
 * This annotation tells kfixture
 * use customization provided in sequence after generation and then provide value to user
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
annotation class Customized(
    val sequence: Array<KClass<out Customizer<out Any>>>
)