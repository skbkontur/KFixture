package ru.kontur.spring.test.generator.processor

import org.reflections.Reflections
import ru.kontur.spring.test.generator.api.ValidateAnnotation
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ValidatorFor
import ru.kontur.spring.test.generator.exceptions.NotResolverException
import ru.kontur.spring.test.generator.exceptions.NotValidateAnnotationException
import ru.kontur.spring.test.generator.exceptions.ResolverNotFoundException
import kotlin.reflect.KClass

/**
 * @author Konstantin Volivach
 * Scan users code and get validations and resolvers for them
 */
class CustomAnnotationProcessor(private val path: String) {
    private val reflections: Reflections = Reflections(path)

    fun getValidatorsMap(): Map<KClass<out Annotation>, ValidationParamResolver> {
        val validators = reflections.getTypesAnnotatedWith(ValidateAnnotation::class.java)
        val resolvers = reflections.getTypesAnnotatedWith(ValidatorFor::class.java)

        val map = mutableMapOf<KClass<out Annotation>, ValidationParamResolver>()
        for (validator in validators) {
            val annotation = validator as? Annotation ?: throw NotValidateAnnotationException(validator.kotlin)

            val paramResolver = resolvers.firstOrNull { it.getAnnotation(ValidatorFor::class.java).value == validator }
                ?: throw ResolverNotFoundException(validator.kotlin)
            if (paramResolver is ValidationParamResolver) {
                map[annotation.annotationClass] = paramResolver
            } else {
                throw NotResolverException(paramResolver.kotlin)
            }
        }
        return map
    }
}