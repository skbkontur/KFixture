package ru.kontur.spring.test.generator.api

import kotlin.reflect.KClass

annotation class ResolverFor(
    val value: KClass<out Annotation>
)