package ru.kontur.test.kfixture.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.annotations.JavaxFixture
import ru.kontur.test.kfixture.api.FixtureMetaGenerator
import ru.kontur.test.kfixture.processor.GeneratorAnnotationScanner
import ru.kontur.test.kfixture.resolver.strategy.FixtureResolverStrategy
import ru.kontur.test.kfixture.resolver.strategy.JavaxFixtureResolverStrategy

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
            it is FixtureMetaGenerator
        } as? FixtureMetaGenerator
        val annotationScanner = GeneratorAnnotationScanner(
            meta?.pathes?.toList() ?: listOf()
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