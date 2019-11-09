package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ResolverFor
import ru.kontur.spring.test.generator.exceptions.NoSuchCaseException
import ru.kontur.spring.test.generator.utils.generateString
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