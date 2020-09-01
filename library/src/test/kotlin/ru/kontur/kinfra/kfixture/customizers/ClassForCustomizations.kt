package ru.kontur.kinfra.kfixture.customizers

import javax.validation.constraints.Size

internal data class ClassForCustomizations(
    @field:Size(max = 1)
    val param: String,
    val param1: String,
    val param2: String
)