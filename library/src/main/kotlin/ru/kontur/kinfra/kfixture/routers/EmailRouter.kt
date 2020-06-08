package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.EmailGenerator
import javax.validation.constraints.Email
import kotlin.reflect.KClass
import kotlin.reflect.KType

class EmailRouter<T> : ValidRouter<T, Email> where T : Any {
    private val emailGenerator = EmailGenerator()

    override fun process(param: T, annotation: Email, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is String -> emailGenerator.process(param, annotation, clazz, type)
            else -> throw AnnotationCantBeAppliedException(annotation, clazz)
        }
    }
}