package ru.kontur.spring.test.generator.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import ru.kontur.spring.test.generator.annotations.Generate

class GenerateParameterResolver : ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.annotations.map { it::class }.contains(Generate::class)
    }

    override fun resolveParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}