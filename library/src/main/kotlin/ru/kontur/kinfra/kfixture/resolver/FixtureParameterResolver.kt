package ru.kontur.kinfra.kfixture.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.slf4j.LoggerFactory
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.exceptions.NotAnnotatedException
import ru.kontur.kinfra.kfixture.resolver.strategy.FixtureResolverStrategy
import ru.kontur.kinfra.kfixture.resolver.strategy.JavaxFixtureResolverStrategy
import ru.kontur.kinfra.kfixture.scanner.CachedScanner
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.findAnnotation

/**
 * @author Konstantin Volivach
 */
class FixtureParameterResolver() : ParameterResolver {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val cachedReflections: CachedScanner = CachedScanner()

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.annotations.filterIsInstance<Fixture>().isNotEmpty() ||
                parameterContext.parameter.annotations.filterIsInstance<JavaxFixture>().isNotEmpty()
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val meta =
            extensionContext.testInstance.get()::class.findAnnotationEverywhere<FixtureGeneratorMeta>()?.also {
                logger.info("Found fixture meta")
            }

        val annotationScanner = cachedReflections.getScanner(
            paths = meta?.pathes?.toList() ?: listOf(),
            extensionContext = extensionContext
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
                throw NotAnnotatedException(parameterContext.parameter.name)
            }
        }
    }

    private inline fun <reified T : Annotation> KClass<*>.findAnnotationEverywhere(): T? {
        return this.findAnnotationWithInheritance() ?: this.findAnnotationOfAnnotations()
        ?: findAnnotationOnOuterClasses()
    }

    private inline fun <reified T : Annotation> KClass<*>.findAnnotationOnOuterClasses(): T? {
        var annotation: T? = null
        var outer = this.java.declaringClass
        while (annotation == null && outer != null) {
            annotation = outer.kotlin.findAnnotationWithInheritance() ?: outer.kotlin.findAnnotationOfAnnotations()
            outer = outer.declaringClass
        }

        return annotation
    }

    private inline fun <reified T : Annotation> KClass<*>.findAnnotationWithInheritance(): T? {
        val classes = this.allSuperclasses
        return (classes + this).mapNotNull { it.findAnnotation<T>() }.firstOrNull()
    }

    private inline fun <reified T : Annotation> KClass<*>.findAnnotationOfAnnotations(): T? {
        val annotationOfAnnotation = annotations.mapNotNull {
            it.annotationClass.findAnnotation<T>()
        }.firstOrNull()
        val annotationOnSuper = annotations.mapNotNull {
            it.annotationClass.findAnnotationWithInheritance<T>()
        }.firstOrNull()

        return annotationOfAnnotation ?: annotationOnSuper
    }
}