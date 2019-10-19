package ru.kontur.spring.test.generator.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import ru.kontur.spring.test.generator.annotations.Generate
import ru.kontur.spring.test.generator.processor.ClassProcessor

class GenerateParameterResolver : ParameterResolver {

    private val processor = ClassProcessor()

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.annotations.filter { it is Generate }.isNotEmpty()
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        return processor.generate(parameterContext, extensionContext)
    }
}