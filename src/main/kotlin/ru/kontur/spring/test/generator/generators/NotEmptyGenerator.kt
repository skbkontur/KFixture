package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.ValidationParamResolver

/**
 * @author Konstantin Volivach
 */
class NotEmptyGenerator<T> : ValidationParamResolver<T> {
    override fun process(param: T): T {
        when (param) {
            is Map<*, *> -> {
                if (param.isEmpty()) {
                    //TODO generate map
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