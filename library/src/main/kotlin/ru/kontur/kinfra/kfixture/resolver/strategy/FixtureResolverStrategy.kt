package ru.kontur.kinfra.kfixture.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.kinfra.kfixture.api.Customizer
import ru.kontur.kinfra.kfixture.context.FixtureContext
import ru.kontur.kinfra.kfixture.exceptions.FixtureGenerationException
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.resolver.ResolverStrategy
import ru.kontur.kinfra.kfixture.misc.toKType

class FixtureResolverStrategy(
    private val fixtureProcessor: FixtureProcessor
) : ResolverStrategy {
    private val context = FixtureContext(fixtureProcessor)

    override fun resolve(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext,
        customized: List<Customizer<Any>>
    ): Any {
        val type = parameterContext.parameter.type
        val kType = parameterContext.parameter.parameterizedType
        val generated = fixtureProcessor.generateParam(type.kotlin, kType.toKType(), null)
            ?: throw FixtureGenerationException(kType.typeName, parameterContext.parameter.name)
        // TODO should check that customizers class match with generated param
        return customized.foldRight(generated) { t, acc ->
            t.customize(acc, context)
        }
    }
}