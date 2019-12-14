package ru.kontur.kinfra.kfixture.api

import kotlin.reflect.KClass

annotation class ResolverFor(
    val value: KClass<out Annotation>
)