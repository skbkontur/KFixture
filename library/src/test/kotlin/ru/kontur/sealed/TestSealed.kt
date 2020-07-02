package ru.kontur.sealed

sealed class TestSealed(
    val param: String
)

class ConcreteClass(
    param: String,
    val secondParam: String
) : TestSealed(
    param
)