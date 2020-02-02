package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import ru.kontur.kinfra.kfixture.api.ResolverFor
import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.NotBlank
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ResolverFor(value = NotBlank::class)
class NotBlankGenerator : ValidationParamResolver {
    private companion object {
        const val DEFAULT_SIZE = 10
    }

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        when (clazz) {
            String::class -> {
                if (generatedParam == null || generatedParam is String && generatedParam.isBlank()) {
                    return generateString(DEFAULT_SIZE)
                }
            }
        }
        return generatedParam
    }
}

class NotBlankGenerator2 : ValidParamGenerator<String, NotBlank> {
    override fun process(param: String?, annotation: NotBlank): String {
        if (param == null || param.isBlank()) {
            return generateString(DEFAULT_SIZE)
        }
        return param
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}