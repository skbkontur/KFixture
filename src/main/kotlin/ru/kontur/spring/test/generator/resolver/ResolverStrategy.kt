package ru.kontur.spring.test.generator.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext

interface ResolverStrategy {
    fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any
}