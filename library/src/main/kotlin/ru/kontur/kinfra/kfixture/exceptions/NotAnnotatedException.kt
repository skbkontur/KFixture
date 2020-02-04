package ru.kontur.kinfra.kfixture.exceptions

class NotAnnotatedException(
    parameterName: String
) : IllegalArgumentException("Class wasn't annotated, name=$parameterName")