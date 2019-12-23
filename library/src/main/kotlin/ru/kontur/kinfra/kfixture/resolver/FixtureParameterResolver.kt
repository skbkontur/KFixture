package ru.kontur.kinfra.kfixture.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.processor.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.resolver.strategy.FixtureResolverStrategy
import ru.kontur.kinfra.kfixture.resolver.strategy.JavaxFixtureResolverStrategy

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
            it is FixtureGeneratorMeta
        } as? FixtureGeneratorMeta

        val annotationScanner = GeneratorAnnotationScanner(
            getReflections(paths = meta?.pathes?.toList() ?: listOf(), extensionContext = extensionContext)
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

    private fun getReflections(paths: List<String>, extensionContext: ExtensionContext): Reflections {
        return Reflections(paths + listOf(LIBRARY_PATH, JAVAX_PATH))
//        return
//        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
//            .get(DEFAULT_REFLECTION_KEY, Reflections::class.java)
//            ?: if (extensionContext.parent.isPresent) {
//                getReflections(paths, extensionContext.parent.get())
//            } else {
//                val reflections = Reflections(paths + listOf(LIBRARY_PATH, JAVAX_PATH))
//                extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put(DEFAULT_REFLECTION_KEY, reflections)
//                reflections
//            }
    }

    private companion object {
        const val DEFAULT_REFLECTION_KEY = "REFLECTIONS_REFERENCES"
        const val LIBRARY_PATH = "ru.kontur.test.kfixture"
        const val JAVAX_PATH = "javax.validation.constraints"
    }
}