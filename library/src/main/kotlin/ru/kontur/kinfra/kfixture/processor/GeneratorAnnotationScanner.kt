package ru.kontur.kinfra.kfixture.processor

import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import ru.kontur.kinfra.kfixture.routers.ValidRouter
import kotlin.reflect.KClass

/**
 * @author Konstantin Volivach
 * Scan users code and get validations and resolvers for them
 */
class GeneratorAnnotationScanner(
    private val reflections: Reflections
) {
    constructor(paths: List<String>) : this(Reflections(paths + listOf(LIBRARY_PATH, JAVAX_PATH)))

    fun getValidatorsMap(): Map<KClass<out Annotation>, ValidRouter<Any, Any>> {
        return internalValidatorsMap()
    }

    fun getValidationConstructors(): Map<KClass<*>, ValidationConstructor<*>> {
        val constructors = reflections.getSubTypesOf(ValidationConstructor::class.java).map { it.kotlin }

        return constructors.associate<KClass<out ValidationConstructor<*>>, KClass<out Any>, ValidationConstructor<*>> {
            val constructor = it.constructors.toMutableList()[0]
            val validationConstructor = constructor.call()
            it.java.getDeclaredMethod("call").returnType.kotlin to validationConstructor
        }
    }

    fun getConstructors(): Map<KClass<*>, ParamConstructor<*>> {
        val constructors = reflections.getSubTypesOf(ParamConstructor::class.java).map { it.kotlin }

        return constructors.associate<KClass<out ParamConstructor<*>>, KClass<out Any>, ParamConstructor<*>> {
            val constructor = it.constructors.toMutableList()[0]
            val paramConstructor = constructor.call()
            it.java.getDeclaredMethod("call").returnType.kotlin to paramConstructor
        }
    }

    fun getSubTypeOf(clazz: KClass<*>): Class<*> {
        return reflections.getSubTypesOf(clazz.java).firstOrNull()
            ?: throw IllegalArgumentException("Collection of subType is empty, clazz=${clazz.qualifiedName}")
    }

    private fun internalValidatorsMap(): Map<KClass<out Annotation>, ValidRouter<Any, Any>> {
        val routers = reflections.getSubTypesOf(ValidRouter::class.java)
        val transformed = routers.map {
            it.kotlin
        }

        val routersMap = mutableMapOf<KClass<out Annotation>, ValidRouter<*, *>>()
        for (router in transformed) {
            val annotation = router.supertypes[0].arguments[1].type

            val constructor = router.constructors.toMutableList()[0]
            val routerImpl = constructor.call()
            routersMap[annotation?.classifier as KClass<out Annotation>] = routerImpl
        }
        return routersMap as Map<KClass<out Annotation>, ValidRouter<Any, Any>>
    }

    private companion object {
        const val LIBRARY_PATH = "ru.kontur.kinfra.kfixture"
        const val JAVAX_PATH = "javax.validation.constraints"
    }
}