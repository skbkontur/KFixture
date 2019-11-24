package ru.kontur.test.kfixture.api

import kotlin.reflect.KClass

annotation class ResolverFor(
    val value: KClass<out Annotation>
)