package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.NotNullGenerator
import javax.validation.constraints.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class NotNullRouter<T> : ValidRouter<T, NotNull> where T : Any {
    private val notNullGenerator: NotNullGenerator<Any> = NotNullGenerator()

    override fun process(param: T, annotation: NotNull, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Any -> notNullGenerator.process(param, annotation, clazz, type)
            else -> throw AnnotationCantBeAppliedException(annotation, clazz)
        }
    }
}