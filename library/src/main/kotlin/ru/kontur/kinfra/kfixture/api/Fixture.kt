package ru.kontur.kinfra.kfixture.api

@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
annotation class Fixture(
    val group: String = "default"
)