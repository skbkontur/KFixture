package ru.kontur.kinfra.kfixture.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.slf4j.LoggerFactory
import ru.kontur.kinfra.kfixture.api.*
import ru.kontur.kinfra.kfixture.annotations.Fixture as OldFixture
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture as OldJavaxFixture
import ru.kontur.kinfra.kfixture.converter.CollectionSettingsConverter
import ru.kontur.kinfra.kfixture.exceptions.NotAnnotatedException
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.resolver.strategy.FixtureResolverStrategy
import ru.kontur.kinfra.kfixture.resolver.strategy.JavaxFixtureResolverStrategy
import ru.kontur.kinfra.kfixture.scanner.CachedScanner
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

/**
 * @author Konstantin Volivach
 */
class FixtureParameterResolver : ParameterResolver {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val cachedReflections: CachedScanner = CachedScanner()

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.isAnnotated(Fixture::class.java) ||
            parameterContext.isAnnotated(JavaxFixture::class.java) ||
            parameterContext.isAnnotated(OldFixture::class.java) ||
            parameterContext.isAnnotated(OldJavaxFixture::class.java) ||
            extensionContext.requiredTestClass.isAnnotationPresent(Fixture::class.java) ||
            extensionContext.requiredTestClass.isAnnotationPresent(JavaxFixture::class.java)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val meta =
            extensionContext.testInstance.get()::class.findAnnotationEverywhere<FixtureGeneratorMeta>()?.also {
                logger.info("Found fixture meta")
            }

        val paths = (meta?.pathes?.toList() ?: listOf()) + (meta?.scanner?.paths?.toList() ?: listOf())
        val annotationScanner = cachedReflections.getScanner(
            paths = paths,
            extensionContext = extensionContext
        )

        val fixture = parameterContext.findAnnotation(Fixture::class.java).orElse(null)
            ?: parameterContext.findAnnotation(OldFixture::class.java).orElse(null)
            ?: extensionContext.requiredTestClass.getAnnotation(Fixture::class.java)
        val javaxFixture = parameterContext.findAnnotation(JavaxFixture::class.java).orElse(null)
            ?: parameterContext.findAnnotation(OldJavaxFixture::class.java).orElse(null)
            ?: extensionContext.requiredTestClass.getAnnotation(JavaxFixture::class.java)

        val fixtureCustomizations = parameterContext.findAnnotation(Customized::class.java).orElse(null)?.takeIf {
            it.sequence.isNotEmpty()
        }?.let { resolveCustomizationsClasses(it) } ?: listOf()
        return when {
            fixture != null -> {
                val fixtureProcessor =
                    cachedReflections.getFixtureProcessor(
                        paths,
                        meta?.collection?.let { CollectionSettingsConverter.convert(it) } ?: CollectionSettings(),
                        extensionContext)
                FixtureResolverStrategy(fixtureProcessor).resolve(
                    parameterContext,
                    extensionContext,
                    fixtureCustomizations
                )
            }
            javaxFixture != null -> {
                JavaxFixtureResolverStrategy(
                    annotationScanner
                ).resolve(parameterContext, extensionContext, fixtureCustomizations)
            }
            else -> {
                throw NotAnnotatedException(parameterContext.parameter.name)
            }
        }
    }

    private fun resolveCustomizationsClasses(customized: Customized): List<Customizer<Any>> {
        return customized.sequence.mapNotNull { it.primaryConstructor?.call() as? Customizer<Any> }
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