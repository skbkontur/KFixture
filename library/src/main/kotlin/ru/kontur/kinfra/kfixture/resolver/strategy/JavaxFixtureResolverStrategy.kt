package ru.kontur.kinfra.kfixture.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.kinfra.kfixture.exceptions.FixtureGenerationException
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScannerImpl
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.processor.impl.JavaxFixtureProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.resolver.ResolverStrategy
import ru.kontur.kinfra.kfixture.utils.toKType

class JavaxFixtureResolverStrategy(
    private val generatorAnnotationScanner: GeneratorAnnotationScanner
) : ResolverStrategy {
    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val generators = generatorAnnotationScanner.getValidatorsMap()

        val constructors = generatorAnnotationScanner.getConstructors() // TODO optimize
        val validationConstructors = generatorAnnotationScanner.getValidationConstructors() // TODO optimize

        val fixtureProcessor = FixtureProcessor(CollectionSettings(), generatorAnnotationScanner)

        val classProcessor =
            JavaxFixtureProcessor(
                CollectionSettings(),
                generators,
                validationConstructors,
                constructors,
                generatorAnnotationScanner,
                fixtureProcessor
            )

        val type = parameterContext.parameter.type
        return classProcessor.generateParam(type.kotlin, type.toKType(), null)
            ?: throw FixtureGenerationException(type.typeName, parameterContext.parameter.name)
    }
}