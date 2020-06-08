package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.NotBlankGenerator
import javax.validation.constraints.NotBlank
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class NotBlankRouter<T> : ValidParamGenerator<T, NotBlank> where T : Any {
    private val notBlankGenerator = NotBlankGenerator()

    override fun process(param: T, annotation: NotBlank, clazz: KClass<*>, type: KType): T? {
        return when (param) {
            is String -> notBlankGenerator.process(param, annotation, clazz, type)
            else -> throw AnnotationCantBeAppliedException(annotation, clazz)
        } as? T?
    }
}