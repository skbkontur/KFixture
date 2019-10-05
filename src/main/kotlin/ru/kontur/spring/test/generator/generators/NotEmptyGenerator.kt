package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.ValidationParamResolver
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