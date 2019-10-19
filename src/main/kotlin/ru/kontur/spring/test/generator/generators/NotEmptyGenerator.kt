package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.exceptions.NoSuchCaseException
import ru.kontur.spring.test.generator.utils.generateCollection
import ru.kontur.spring.test.generator.utils.generateMap
import ru.kontur.spring.test.generator.utils.generateString
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class NotEmptyGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType): T {
        when (clazz) {
            Map::class -> {
                if (generatedParam == null || generatedParam is Map<*, *> && generatedParam.isEmpty()) {
                    return generateMap(DEFAULT_SIZE, clazz, type) as T
                }
            }
            Collection::class -> {
                if (generatedParam == null || generatedParam is Collection<*> && generatedParam.isEmpty()) {
                    return generateCollection(DEFAULT_SIZE, clazz, type) as T
                }
            }
            String::class -> {
                if (generatedParam == null && generatedParam is String || generatedParam is String && generatedParam.isEmpty()) {
                    return generateString(DEFAULT_SIZE) as T
                }
            }
            else -> {
                throw NoSuchCaseException("case not found for param=${clazz.simpleName}")
            }
        }
        return generatedParam!!
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}