package ru.kontur.spring.test.generator.processor

import org.reflections.Reflections
import ru.kontur.spring.test.generator.api.ResolverFor
import ru.kontur.spring.test.generator.api.ValidationConstructor
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.exceptions.NotValidateAnnotationException
import javax.validation.Constraint
import kotlin.reflect.KClass

/**
 * @author Konstantin Volivach
 * Scan users code and get validations and resolvers for them
 */
class GeneratorAnnotationScanner(
    pathes: List<String>
) {
    private val reflections: Reflections = Reflections(listOf(pathes, LIBRARY_PATH, JAVAX_PATH))

    fun getValidatorsMap(): Map<KClass<out Annotation>, ValidationParamResolver> {
        return internalValidatorsMap()
    }

    fun getConstructors(): Map<KClass<*>, ValidationConstructor<*>> {
        val constructors = reflections.getSubTypesOf(ValidationConstructor::class.java).map { it.kotlin }

        return constructors.associate<KClass<out ValidationConstructor<*>>, KClass<out Any>, ValidationConstructor<*>> {
            val constructor = it.constructors.toMutableList()[0]
            val validationConstructor = constructor.call()
            it.java.getDeclaredMethod("call").returnType.kotlin to validationConstructor
        }
    }

    fun getSubTypeOf(clazz: KClass<*>): Class<*> {
        return reflections.getSubTypesOf(clazz.java).firstOrNull()
            ?: throw IllegalArgumentException("Collection of subType is empty, clazz=${clazz.qualifiedName}")
    }

    private fun internalValidatorsMap(): Map<KClass<out Annotation>, ValidationParamResolver> {
        val annotationValidators = reflections.getTypesAnnotatedWith(Constraint::class.java).map { it.kotlin }
        val resolvers = reflections.getTypesAnnotatedWith(ResolverFor::class.java).map { it.kotlin }

        val map = mutableMapOf<KClass<out Annotation>, ValidationParamResolver>()
        for (annotationClass in annotationValidators) {
            annotationClass as? KClass<out Annotation> ?: throw NotValidateAnnotationException(
                annotationClass
            )

            val paramResolver =
                resolvers.firstOrNull { it.java.getAnnotation(ResolverFor::class.java).value == annotationClass }

            val constructor = paramResolver?.constructors?.toMutableList()?.get(0)
            val resolver = constructor?.call()

            if (resolver is ValidationParamResolver) {
                map[annotationClass] = resolver
            }
        }
        return map
    }

    private companion object {
        const val LIBRARY_PATH = "ru.kontur.spring.test.generator"
        const val JAVAX_PATH = "javax.validation.constraints"
    }
}