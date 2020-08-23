package ru.kontur.kinfra.kfixture.api

@Target(AnnotationTarget.CLASS)
annotation class Constructor(
    val group: String = "default"
)