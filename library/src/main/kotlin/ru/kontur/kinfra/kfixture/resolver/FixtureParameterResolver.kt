package ru.kontur.kinfra.kfixture.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.slf4j.LoggerFactory
import ru.kontur.kinfra.kfixture.api.*
import ru.kontur.kinfra.kfixture.converter.CollectionSettingsConverter
import ru.kontur.kinfra.kfixture.exceptions.NotAnnotatedException
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.resolver.strategy.FixtureResolverStrategy
import ru.kontur.kinfra.kfixture.resolver.strategy.JavaxFixtureResolverStrategy
import ru.kontur.kinfra.kfixture.scanner.GlobalJunitCache
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

/**
 * @author Konstantin Volivach
 */
class FixtureParameterResolver : ParameterResolver {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val globalCache: GlobalJunitCache = GlobalJunitCache()

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.isAnnotated(Fixture::class.java) ||
                parameterContext.isAnnotated(JavaxFixture::class.java) ||
                extensionContext.requiredTestClass.isAnnotationPresent(Fixture::class.java) ||
                extensionContext.requiredTestClass.isAnnotationPresent(JavaxFixture::class.java)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val meta = extensionContext.testInstance.get()::class.findAnnotationEverywhere<FixtureGeneratorMeta>()?.also {
            logger.info("Found fixture meta")
        }

        if (meta != null && meta.cacheSettings.type == CacheType.FILE_SYSTEM) {
            val keyString = ""
            val cacheData = globalCache.getDataCache(extensionContext)?.get(keyString)
            if (cacheData == null) {
                // TODO генерим и заполняем первым значением кеш
            } else {
                return cacheData
            }
        }

        val paths = (meta?.scanner?.paths?.toList() ?: listOf())

        val fixture = parameterContext.findAnnotation(Fixture::class.java).orElse(null)
            ?: extensionContext.requiredTestClass.getAnnotation(Fixture::class.java)
        val javaxFixture = parameterContext.findAnnotation(JavaxFixture::class.java).orElse(null)
            ?: extensionContext.requiredTestClass.getAnnotation(JavaxFixture::class.java)

        val fixtureCustomizations = parameterContext.findAnnotation(Customized::class.java).orElse(null)?.takeIf {
            it.sequence.isNotEmpty()
        }?.let { resolveCustomizationsClasses(it) } ?: listOf()
        return when {
            fixture != null -> {
                val fixtureProcessor = globalCache.getFixtureProcessor(
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
                val annotationScanner = globalCache.getScanner(
                    paths = paths,
                    extensionContext = extensionContext
                )
                JavaxFixtureResolverStrategy(
                    annotationScanner
                ).resolve(parameterContext, extensionContext, fixtureCustomizations)
            }
            else -> {
                throw NotAnnotatedException(parameterContext.parameter.name)
            }
        }
    }

    private fun getCacheKey(parameterContext: ParameterContext, extensionContext: ExtensionContext): String {
        TODO()
    }

    private fun resolveCustomizationsClasses(customized: Customized): List<Customizer<Any>> {
        return customized.sequence.map {
            val primaryConstructor =
                requireNotNull(it.primaryConstructor) { "Customized supports only primary constructors" }
            primaryConstructor as Customizer<Any>
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