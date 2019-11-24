package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.exceptions.NoSuchCaseException
import ru.kontur.test.kfixture.utils.generateCollection
import ru.kontur.test.kfixture.utils.generateMap
import ru.kontur.test.kfixture.utils.generateString
import javax.validation.constraints.NotEmpty
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ResolverFor(value = NotEmpty::class)
class NotEmptyGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        when (clazz) {
            Map::class -> {
                if (generatedParam == null || generatedParam is Map<*, *> && generatedParam.isEmpty()) {
                    return generateMap(DEFAULT_SIZE, clazz, type)
                }
            }
            Collection::class -> {
                if (generatedParam == null || generatedParam is Collection<*> && generatedParam.isEmpty()) {
                    return generateCollection(DEFAULT_SIZE, clazz, type)
                }
            }
            List::class -> {
                if (generatedParam == null || generatedParam is Collection<*> && generatedParam.isEmpty()) {
                    return generateCollection(DEFAULT_SIZE, clazz, type)
                }
            }
            String::class -> {
                if (generatedParam == null || generatedParam is String || generatedParam is String && generatedParam.isEmpty()) {
                    return generateString(DEFAULT_SIZE)
                }
            }
            else -> {
                throw NoSuchCaseException("case not found for param=${clazz.simpleName}")
            }
        }
        return generatedParam
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}