package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.AssertFalseGenerator
import javax.validation.constraints.AssertFalse
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class AssertFalseRouter<T> : ValidRouter<T, AssertFalse> where T : Comparable<T>, T : Any {
    private val assertFalseGenerator = AssertFalseGenerator()

    override fun process(param: T, annotation: AssertFalse, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Boolean -> assertFalseGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}