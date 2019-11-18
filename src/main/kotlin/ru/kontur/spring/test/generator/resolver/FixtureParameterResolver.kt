package ru.kontur.spring.test.generator.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import ru.kontur.spring.test.generator.annotations.Fixture
import ru.kontur.spring.test.generator.annotations.JavaxFixture
import ru.kontur.spring.test.generator.api.FixtureGenerator
import ru.kontur.spring.test.generator.processor.GeneratorAnnotationScanner
import ru.kontur.spring.test.generator.resolver.strategy.FixtureResolverStrategy
import ru.kontur.spring.test.generator.resolver.strategy.JavaxFixtureResolverStrategy

/**
 * @author Konstantin Volivach
 */
class FixtureParameterResolver : ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.annotations.filterIsInstance<Fixture>().isNotEmpty() ||
            parameterContext.parameter.annotations.filterIsInstance<JavaxFixture>().isNotEmpty()
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val meta = extensionContext.testInstance.get()::class.annotations.firstOrNull {
            it is FixtureGenerator
        } as? FixtureGenerator
        val annotationScanner = GeneratorAnnotationScanner(
            meta?.value?.toList() ?: listOf()
        )

        val fixture = parameterContext.parameter.annotations.filterIsInstance<Fixture>()
        val javaxFixture = parameterContext.parameter.annotations.filterIsInstance<JavaxFixture>()

        return when {
            fixture.isNotEmpty() -> {
                val fixtureStrategy = FixtureResolverStrategy(
                    annotationScanner
                )
                fixtureStrategy.resolve(parameterContext, extensionContext)
            }
            javaxFixture.isNotEmpty() -> {
                val javaxStrategy = JavaxFixtureResolverStrategy(
                    annotationScanner
                )
                javaxStrategy.resolve(parameterContext, extensionContext)
            }
            else -> {
                throw IllegalArgumentException("Class was not annotated, something went wrong")
            }
        }
    }
}