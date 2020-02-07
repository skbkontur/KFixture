package ru.kontur.kinfra.kfixture.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.kinfra.kfixture.exceptions.FixtureGenerationException
import ru.kontur.kinfra.kfixture.processor.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.processor.impl.JavaxFixtureProcessor
import ru.kontur.kinfra.kfixture.resolver.ResolverStrategy
import ru.kontur.kinfra.kfixture.utils.toKType

class JavaxFixtureResolverStrategy(
    private val generatorAnnotationScanner: GeneratorAnnotationScanner
) : ResolverStrategy {
    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val generators = generatorAnnotationScanner.getValidatorsMap()

        val constructors = generatorAnnotationScanner.getConstructors()

        val fixtureProcessor = FixtureProcessor(constructors, generatorAnnotationScanner)

        val classProcessor =
            JavaxFixtureProcessor(generators, constructors, generatorAnnotationScanner, fixtureProcessor)

        val type = parameterContext.parameter.type
        return classProcessor.generateParam(type.kotlin, type.toKType(), null)
            ?: throw FixtureGenerationException(type.typeName, parameterContext.parameter.name)
    }
}