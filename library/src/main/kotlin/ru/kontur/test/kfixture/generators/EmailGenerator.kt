package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.exceptions.NoSuchCaseException
import ru.kontur.test.kfixture.utils.generateString
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