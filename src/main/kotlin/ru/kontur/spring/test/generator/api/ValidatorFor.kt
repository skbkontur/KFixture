package ru.kontur.spring.test.generator.api

import kotlin.reflect.KClass

annotation class ValidatorFor(
    val value: KClass<out Annotation>
)