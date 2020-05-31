package ru.kontur.kinfra.kfixture.exceptions

import java.lang.RuntimeException
import kotlin.reflect.KClass

class AnnotationCantBeAppliedException(annotation: Any, clazz: KClass<*>) : RuntimeException(
    "${annotation::class.simpleName} annotation can't get applied to type ${clazz.simpleName}"
)