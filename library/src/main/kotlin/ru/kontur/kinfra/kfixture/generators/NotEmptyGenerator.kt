package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.NotEmpty

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

    override fun process(param: T, annotation: NotEmpty): T {
        TODO("Generate not empty structures is not implemented")
    }
}