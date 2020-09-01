package ru.kontur.kinfra.kfixture.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.kinfra.kfixture.api.Customizer

interface ResolverStrategy {
    fun resolve(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext,
        customized: List<Customizer<Any>>
    ): Any
}