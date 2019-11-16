package ru.kontur.spring.test.generator.resolver.strategy

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import ru.kontur.spring.test.generator.api.ValidationConstructor
import ru.kontur.spring.test.generator.resolver.ResolverStrategy
import kotlin.reflect.KClass

class FixtureResolverStrategy(
    private val defaultConstructors: Map<KClass<*>, ValidationConstructor<*>>
) : ResolverStrategy {

    override fun resolve(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}