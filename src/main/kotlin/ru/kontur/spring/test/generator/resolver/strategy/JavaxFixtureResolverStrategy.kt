package ru.kontur.spring.test.generator.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.processor.processors.JavaxFixtureProcessor
import ru.kontur.spring.test.generator.processor.GeneratorAnnotationScanner
import ru.kontur.spring.test.generator.resolver.ResolverStrategy
import ru.kontur.spring.test.generator.utils.toKType
import java.lang.RuntimeException

class JavaxFixtureResolverStrategy(
    private val generatorAnnotationScanner: GeneratorAnnotationScanner
) : ResolverStrategy {
    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val generators = generatorAnnotationScanner.getValidatorsMap()

        val constructors = generatorAnnotationScanner.getConstructors()

        val classProcessor =
            JavaxFixtureProcessor(generators, constructors, generatorAnnotationScanner)

        val type = parameterContext.parameter.type
        return classProcessor.generateParam(type.kotlin, type.toKType(), null)
            ?: throw RuntimeException("Something went wrong")
    }
}