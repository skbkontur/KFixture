package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.misc.generateString
import javax.validation.constraints.Email
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class EmailGenerator : ValidParamGenerator<String, Email> {
    override fun process(
        param: String,
        annotation: Email,
        clazz: KClass<*>,
        type: KType
    ): String? {
        return generateEmail()
    }

    private fun generateEmail(): String {
        return generateString(DEFAULT_NUMBER) + "@gmail.com"
    }

    private companion object {
        const val DEFAULT_NUMBER = 10
    }
}