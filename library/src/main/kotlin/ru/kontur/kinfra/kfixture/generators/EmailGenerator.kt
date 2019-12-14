package ru.kontur.kinfra.kfixture.generators

import ru.kontur.kinfra.kfixture.api.ValidationParamResolver
import ru.kontur.kinfra.kfixture.api.ResolverFor
import ru.kontur.kinfra.kfixture.exceptions.NoSuchCaseException
import ru.kontur.kinfra.kfixture.utils.generateString
import javax.validation.constraints.Email
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ResolverFor(value = Email::class)
class EmailGenerator : ValidationParamResolver {
    private companion object {
        const val DEFAULT_NUMBER = 10
    }

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        when (clazz) {
            String::class.java -> {
                return generateEmail()
            }
            else -> {
                throw NoSuchCaseException("Can't find type for this annotation ${clazz.simpleName}")
            }
        }
    }

    private fun generateEmail(): String {
        return generateString(DEFAULT_NUMBER) + "@gmail.com"
    }
}