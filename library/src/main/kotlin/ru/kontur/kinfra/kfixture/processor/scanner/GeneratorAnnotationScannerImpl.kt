package ru.kontur.kinfra.kfixture.processor.scanner

import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import ru.kontur.kinfra.kfixture.routers.ValidRouter
import kotlin.reflect.KClass

/**
 * @author Konstantin Volivach
 * Scan users code and get validations and resolvers for them
 */
class GeneratorAnnotationScannerImpl(
    private val reflections: Reflections
) : GeneratorAnnotationScanner {
    constructor(paths: List<String>) : this(Reflections(paths + listOf(
        LIBRARY_PATH,
        JAVAX_PATH
    )))

    override fun getValidatorsMap(): Map<KClass<out Annotation>, ValidRouter<Any, Any>> {
        return internalValidatorsMap()
    }

    override fun getValidationConstructors(): Map<KClass<*>, ValidationConstructor<*>> {
        val constructors = reflections.getSubTypesOf(ValidationConstructor::class.java).map { it.kotlin }

        return constructors.associate<KClass<out ValidationConstructor<*>>, KClass<out Any>, ValidationConstructor<*>> {
            val constructor = it.constructors.toMutableList()[0]
            val validationConstructor = constructor.call()
            it.java.getDeclaredMethod("call").returnType.kotlin to validationConstructor
        }
    }

    override fun getConstructors(): Map<KClass<*>, ParamConstructor<*>> {
        val constructors = reflections.getSubTypesOf(ParamConstructor::class.java).map { it.kotlin }

        return constructors.associate<KClass<out ParamConstructor<*>>, KClass<out Any>, ParamConstructor<*>> {
            val constructor = it.constructors.toMutableList()[0]
            val paramConstructor = constructor.call()
            it.java.getDeclaredMethod("call").returnType.kotlin to paramConstructor
        }
    }

    override fun getSubTypeOf(clazz: KClass<*>): Class<*>? {
        return reflections.getSubTypesOf(clazz.java).firstOrNull()
    }

    override fun tryToGetSubTypeByAbstractPackage(clazz: KClass<*>): Class<*>? {
        val path = clazz.java.name.substringBeforeLast(".")
        val reflections = Reflections(path)
        return reflections.getSubTypesOf(clazz.java).firstOrNull()
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