package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.PatternGenerator
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class PatternRouter<T> : ValidRouter<T, Pattern> where T : Any {
    private val patternGenerator = PatternGenerator()

    override fun process(param: T, annotation: Pattern, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is String -> {
                patternGenerator.process(param, annotation, clazz, type)
            }
            else -> throw AnnotationCantBeAppliedException(annotation, clazz)
        }
    }
}