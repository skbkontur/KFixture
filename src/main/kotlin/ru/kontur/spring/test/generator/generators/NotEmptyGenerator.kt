package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.ValidationParamResolver
import ru.kontur.spring.test.generator.utils.generateMap

/**
 * @author Konstantin Volivach
 */
class NotEmptyGenerator<T> : ValidationParamResolver<T> {
    override fun process(param: T): T {
        when (param) {
            is Map<*, *> -> {
                if (param.isEmpty()) {
                    TODO("Generate map")
                }
            }
            is Collection<*> -> {
                if (param.isEmpty()) {
                    //TODO generate collection
                }
            }
            is CharSequence -> {
                if (param.isEmpty()) {
                    //TODO generate charSequence
                }
            }
            is String -> {
                if (param.isEmpty()) {
                    //TODO generate string
                }
            }
            else -> {
            }
        }
        return param
    }
}