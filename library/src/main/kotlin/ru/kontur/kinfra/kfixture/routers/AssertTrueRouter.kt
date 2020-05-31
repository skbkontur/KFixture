package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.AssertTrueGenerator
import javax.validation.constraints.AssertTrue
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class AssertTrueRouter<T> : ValidRouter<T, AssertTrue> where T : Any {
    private val assertTrueGenerator = AssertTrueGenerator()

    override fun process(param: T, annotation: AssertTrue, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Boolean -> {
                assertTrueGenerator.process(param, annotation, clazz, type)
            }
            else -> throw AnnotationCantBeAppliedException(annotation, clazz)
        }
    }
}