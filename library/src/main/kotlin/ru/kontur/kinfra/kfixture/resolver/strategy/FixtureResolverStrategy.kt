package ru.kontur.kinfra.kfixture.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.kinfra.kfixture.exceptions.FixtureGenerationException
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.resolver.ResolverStrategy
import ru.kontur.kinfra.kfixture.utils.toKType

class FixtureResolverStrategy(
    private val fixtureProcessor: FixtureProcessor
) : ResolverStrategy {

    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val type = parameterContext.parameter.type
        return if (type.simpleName == LIST_SIMPLE_NAME) {
            val kType = parameterContext.parameter.parameterizedType
            fixtureProcessor.generateParam(type.kotlin, kType.toKType(), null)
                ?: throw FixtureGenerationException(kType.typeName, parameterContext.parameter.name)
        } else {
            fixtureProcessor.generateParam(type.kotlin, type.toKType(), null)
                ?: throw FixtureGenerationException(type.typeName, parameterContext.parameter.name)
        }
    }

    private companion object {
        const val LIST_SIMPLE_NAME = "List"
    }
}