package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateCollection
import ru.kontur.kinfra.kfixture.utils.generateMap
import ru.kontur.kinfra.kfixture.utils.generateString
import java.lang.IllegalArgumentException
import javax.validation.constraints.Size
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

class SizeGenerator<T : Any> : ValidParamGenerator<T, Size> {
    override fun process(
        param: T,
        annotation: Size,
        clazz: KClass<*>,
        type: KType
    ): T? {
        val min = annotation.min
        val max = annotation.max
        val random = Random.nextInt(min, max)
        return when (param) {
            is Collection<*> -> {
                generateCollection(random, clazz, type)
            }
            is List<*> -> {
                generateCollection(random, clazz, type)
            }
            is Map<*, *> -> {
                generateMap(random, clazz, type)
            }
            is String -> {
                generateString(random)
            }
            is Array<*> -> {
                TODO("Generation of array is not implemented")
            }
            else -> {
                throw IllegalArgumentException("Such annotation can't be applied to ${clazz.simpleName}")
            }
        } as T?
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}