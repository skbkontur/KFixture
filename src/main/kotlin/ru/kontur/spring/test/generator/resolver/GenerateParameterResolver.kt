package ru.kontur.spring.test.generator.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import ru.kontur.spring.test.generator.annotations.Generate
import ru.kontur.spring.test.generator.api.SpringTestDataGenerator
import ru.kontur.spring.test.generator.api.ValidationConstructor
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.constructors.UUIDConstructor
import ru.kontur.spring.test.generator.processor.ClassProcessor
import ru.kontur.spring.test.generator.processor.GeneratorAnnotationScanner
import ru.kontur.spring.test.generator.utils.toKType
import java.lang.RuntimeException
import java.util.*
import kotlin.reflect.KClass

class GenerateParameterResolver : ParameterResolver {
    private val defaultGenerators: Map<KClass<out Annotation>, ValidationParamResolver>
    private val defaultConstructors: Map<KClass<*>, ValidationConstructor<*>> = mapOf(
        UUID::class to UUIDConstructor()
    )
    private val generatorAnnotationScanner = GeneratorAnnotationScanner()

    init {
        val validatorsMap = generatorAnnotationScanner.getDefaultValidatorsMap()
        defaultGenerators = validatorsMap
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.annotations.filterIsInstance<Generate>().isNotEmpty()
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val annotation =
            extensionContext.testInstance.get()::class.annotations.firstOrNull { it is SpringTestDataGenerator } as? SpringTestDataGenerator
        val generators = mutableMapOf<KClass<out Annotation>, ValidationParamResolver>()
        generators.putAll(defaultGenerators)

        val constructors = mutableMapOf<KClass<*>, ValidationConstructor<*>>()
        constructors.putAll(defaultConstructors)

        if (annotation != null) {
            val usersGenerators = generatorAnnotationScanner.getValidatorsMap(annotation.value)
            generators.putAll(usersGenerators)
            val usersConstructors = generatorAnnotationScanner.getConstructors(annotation.value)
            constructors.putAll(usersConstructors)
        }

        val classProcessor = ClassProcessor(generators, constructors)

        val type = parameterContext.parameter.type
        return classProcessor.generateParam(type.kotlin, type.toKType(), null)
            ?: throw RuntimeException("Something went wrong")
    }
}