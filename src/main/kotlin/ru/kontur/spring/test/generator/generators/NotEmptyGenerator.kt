package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.ValidationParamResolver
import ru.kontur.spring.test.generator.utils.generateCollection
import ru.kontur.spring.test.generator.utils.generateMap
import ru.kontur.spring.test.generator.utils.generateString
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class NotEmptyGenerator : ValidationParamResolver {
    override fun <T> process(param: T, clazz: KClass<*>, type: KType): T {
        when (param) {
            is Map<*, *> -> {
                if (param.isEmpty()) {
                    generateMap(DEFAULT_SIZE, clazz, type)
                }
            }
            is Collection<*> -> {
                if (param.isEmpty()) {
                    generateCollection(DEFAULT_SIZE, clazz, type)
                }
            }
            is String -> {
                if (param.isEmpty()) {
                    generateString(DEFAULT_SIZE)
                }
            }
            else -> {
                TODO("Not implemented, contact developers")
            }
        }
        return param
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}