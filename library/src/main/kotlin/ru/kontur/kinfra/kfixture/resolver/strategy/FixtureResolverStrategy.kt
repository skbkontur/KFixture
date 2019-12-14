package ru.kontur.kinfra.kfixture.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.kinfra.kfixture.processor.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.processors.FixtureProcessor
import ru.kontur.kinfra.kfixture.resolver.ResolverStrategy
import ru.kontur.kinfra.kfixture.utils.toKType
import java.lang.RuntimeException

class FixtureResolverStrategy(
    private val generatorAnnotationScanner: GeneratorAnnotationScanner
) : ResolverStrategy {

    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val constructors = generatorAnnotationScanner.getConstructors()

        val clazzProcessor = FixtureProcessor(constructors, generatorAnnotationScanner)

        val type = parameterContext.parameter.type
        return clazzProcessor.generateParam(type.kotlin, type.toKType(), null)
            ?: throw RuntimeException("Something went wrong")
    }
}