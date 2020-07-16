package ru.kontur.kinfra.kfixture.processor.scanner

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import ru.kontur.kinfra.kfixture.routers.ValidRouter
import kotlin.reflect.KClass

interface GeneratorAnnotationScanner {
    fun getValidatorsMap(): Map<KClass<out Annotation>, ValidRouter<Any, Any>>
    fun getValidationConstructors(): Map<KClass<*>, ValidationConstructor<*>>
    fun getConstructors(): Map<KClass<*>, ParamConstructor<*>>
    fun getSubTypeOf(clazz: KClass<*>): Class<*>?
    fun tryToGetSubTypeByAbstractPackage(clazz: KClass<*>): Class<*>?
}