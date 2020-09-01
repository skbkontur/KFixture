package ru.kontur.kinfra.kfixture.api

import ru.kontur.kinfra.kfixture.context.FixtureContext

/**
 * Interface for provide customizers for fixtures and cover business cases
 */
interface Customizer<T : Any> {
    /**
     * @param value - generated value or value customized from previous customizer
     * @param context - context to use generation tools
     * @return - customized value which be passed to test or next customizer
     */
    fun customize(value: T, context: FixtureContext): T
}