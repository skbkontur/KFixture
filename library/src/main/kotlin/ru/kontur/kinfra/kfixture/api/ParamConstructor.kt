package ru.kontur.kinfra.kfixture.api

import ru.kontur.kinfra.kfixture.context.FixtureContext

/**
 * Use to constructor difficult param
 * if kfixture can't construct them automatically
 * you can provide construction by self
 */
interface ParamConstructor<T : Any> {
    fun call(context: FixtureContext): T
}