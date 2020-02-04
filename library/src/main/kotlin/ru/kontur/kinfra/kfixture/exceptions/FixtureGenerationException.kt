package ru.kontur.kinfra.kfixture.exceptions

class FixtureGenerationException(
    typeName: String,
    parameterName: String
) : RuntimeException(
    "Something went wrong such parameter can't be generated type=$typeName" +
            " parameterName=$parameterName"
)