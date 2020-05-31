package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateCollection
import ru.kontur.kinfra.kfixture.utils.generateMap
import ru.kontur.kinfra.kfixture.utils.generateString
import java.lang.IllegalArgumentException
import javax.validation.constraints.NotEmpty
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class NotEmptyGenerator<T : Any> : ValidParamGenerator<T, NotEmpty> {
    override fun process(
        param: T,
        annotation: NotEmpty,
        clazz: KClass<*>,
        type: KType
    ): T? {
        return when (param) {
            is Collection<*> -> {
                generateCollection(DEFAULT_SIZE, clazz, type)
            }
            is List<*> -> {
                generateCollection(DEFAULT_SIZE, clazz, type)
            }
            is Map<*, *> -> {
                generateMap(DEFAULT_SIZE, clazz, type)
            }
            is String -> {
                generateString(DEFAULT_SIZE)
            }
            is Array<*> -> {
                TODO("Generation of array is not implemented")
            }
            else -> {
                throw IllegalArgumentException("Such annotation can't be applied to ${param::class.simpleName}")
            }
        } as T?
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}