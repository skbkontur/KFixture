package ru.kontur.spring.test.generator.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.SpringTestDataGenerator
import ru.kontur.spring.test.generator.api.ValidationConstructor
import ru.kontur.spring.test.generator.processor.GeneratorAnnotationScanner
import ru.kontur.spring.test.generator.processor.processors.FixtureProcessor
import ru.kontur.spring.test.generator.resolver.ResolverStrategy
import ru.kontur.spring.test.generator.utils.toKType
import java.lang.RuntimeException
import kotlin.reflect.KClass

class FixtureResolverStrategy(
    private val defaultConstructors: Map<KClass<*>, ValidationConstructor<*>>,
    private val userPath: String?
) : ResolverStrategy {
    private val generatorAnnotationScanner = GeneratorAnnotationScanner()

    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val annotation = extensionContext.testInstance.get()::class.annotations.firstOrNull {
            it is SpringTestDataGenerator
        } as? SpringTestDataGenerator

        val constructors = mutableMapOf<KClass<*>, ValidationConstructor<*>>()
        constructors.putAll(defaultConstructors)

        if (annotation != null) {
            val usersConstructors = generatorAnnotationScanner.getConstructors(annotation.value)
            constructors.putAll(usersConstructors)
        }

        val clazzProcessor = FixtureProcessor(constructors, userPath)

        val type = parameterContext.parameter.type
        return clazzProcessor.generateParam(type.kotlin, type.toKType(), null)
            ?: throw RuntimeException("Something went wrong")
    }
}