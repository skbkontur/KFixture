package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ResolverFor
import ru.kontur.spring.test.generator.utils.generateString
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