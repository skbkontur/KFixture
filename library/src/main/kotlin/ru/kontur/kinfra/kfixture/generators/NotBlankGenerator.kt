package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.NotBlank
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class NotBlankGenerator : ValidParamGenerator<String, NotBlank> {
    override fun process(
        param: String,
        annotation: NotBlank,
        clazz: KClass<*>,
        type: KType
    ): String? {
        if (param.isBlank()) {
            return generateString(DEFAULT_SIZE)
        }
        return param
    }

    private companion object {
        const val DEFAULT_SIZE = 10
    }
}