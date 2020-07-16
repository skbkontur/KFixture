package ru.kontur.kinfra.kfixture.processor.scanner

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import ru.kontur.kinfra.kfixture.routers.ValidRouter
import kotlin.reflect.KClass

class CachedAnnotationScanner(private val scanner: GeneratorAnnotationScanner) : GeneratorAnnotationScanner by scanner {
    private lateinit var validatorMap: Map<KClass<out Annotation>, ValidRouter<Any, Any>>
    private lateinit var validationConstructors: Map<KClass<*>, ValidationConstructor<*>>
    private lateinit var constructors: Map<KClass<*>, ParamConstructor<*>>
    private var subTypeMap: MutableMap<KClass<*>, Class<*>> = mutableMapOf()

    override fun getValidatorsMap(): Map<KClass<out Annotation>, ValidRouter<Any, Any>> {
        if (::validatorMap.isInitialized) return validatorMap
        validatorMap = scanner.getValidatorsMap()
        return validatorMap
    }

    override fun getValidationConstructors(): Map<KClass<*>, ValidationConstructor<*>> {
        if (::validationConstructors.isInitialized) return validationConstructors
        validationConstructors = scanner.getValidationConstructors()
        return validationConstructors
    }

    override fun getConstructors(): Map<KClass<*>, ParamConstructor<*>> {
        if (::constructors.isInitialized) return constructors
        constructors = scanner.getConstructors()
        return constructors
    }

    override fun getSubTypeOf(clazz: KClass<*>): Class<*>? {
        return subTypeMap[clazz] ?: let {
            val subType = scanner.getSubTypeOf(clazz) ?: return null
            subTypeMap[clazz] = subType
            subType
        }
    }

    override fun tryToGetSubTypeByAbstractPackage(clazz: KClass<*>): Class<*>? {
        return subTypeMap[clazz] ?: let {
            val subType = scanner.tryToGetSubTypeByAbstractPackage(clazz) ?: return null
            subTypeMap[clazz] = subType
            subType
        }
    }
}