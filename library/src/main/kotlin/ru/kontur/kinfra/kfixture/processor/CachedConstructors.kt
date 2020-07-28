package ru.kontur.kinfra.kfixture.processor

import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScanner
import ru.kontur.kinfra.kfixture.utils.FixtureUtils
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class CachedConstructors(
    private val scanner: GeneratorAnnotationScanner
) {
    private val cachedConstructors = mutableMapOf<KClass<*>, KFunction<*>>()

    fun getCreatorFunction(clazz: KClass<*>): KFunction<*> {
        val actual = cachedConstructors[clazz] ?: FixtureUtils.getCreatorFunction(clazz, scanner)
        cachedConstructors[clazz] = actual
        return actual
    }
}