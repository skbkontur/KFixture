package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateCollection
import ru.kontur.kinfra.kfixture.utils.toInvariantFlexibleProjection
import java.lang.IllegalArgumentException
import javax.validation.constraints.NotEmpty
import kotlin.reflect.full.starProjectedType

/**
 * @author Konstantin Volivach
 */
class NotEmptyGenerator<T> : ValidParamGenerator<T, NotEmpty> {

//    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
//        when (clazz) {
//            Map::class -> {
//                if (generatedParam == null || generatedParam is Map<*, *> && generatedParam.isEmpty()) {
//                    return generateMap(DEFAULT_SIZE, clazz, type)
//                }
//            }
//            Collection::class -> {
//                if (generatedParam == null || generatedParam is Collection<*> && generatedParam.isEmpty()) {
//                    return generateCollection(DEFAULT_SIZE, clazz, type)
//                }
//            }
//            List::class -> {
//                if (generatedParam == null || generatedParam is Collection<*> && generatedParam.isEmpty()) {
//                    return generateCollection(DEFAULT_SIZE, clazz, type)
//                }
//            }
//            String::class -> {
//                if (generatedParam == null || generatedParam is String || generatedParam is String && generatedParam.isEmpty()) {
//                    return generateString(DEFAULT_SIZE)
//                }
//            }
//            else -> {
//                throw NoSuchCaseException("case not found for param=${clazz.simpleName}")
//            }
//        }
//        return generatedParam
//    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }

    override fun process(param: T, annotation: NotEmpty): T? {
        when (param) {
            is Collection<*> -> {
                return generateCollection()
//                return generateCollection(10, T, T::class.starProjectedType) as T
            }
            is List<*> -> {

            }
            is Map<*, *> -> {
            }
            is String -> {

            }
            is Array<*> -> {

            }
            else -> {
                throw IllegalArgumentException("Such annotation can't be applied to ${param::class.simpleName}")
            }
        }
    }
}