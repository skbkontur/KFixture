package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.NullGenerator
import javax.validation.constraints.Null
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class NullRouter<T> : ValidRouter<T, Null> where T : Any {
    private val nullGenerator: NullGenerator<Any> = NullGenerator()

    override fun process(param: T, annotation: Null, clazz: KClass<*>, type: KType): Any? {
        return nullGenerator.process(param, annotation, clazz, type)
    }
}