package ru.kontur.test.kfixture.generators

import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.utils.generateString
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