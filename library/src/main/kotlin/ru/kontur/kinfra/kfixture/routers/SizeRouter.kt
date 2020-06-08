package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.SizeGenerator
import javax.validation.constraints.Size
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class SizeRouter<T> : ValidRouter<T, Size> where T : Any {
    private val sizeGenerator = SizeGenerator<Any>()

    override fun process(param: T, annotation: Size, clazz: KClass<*>, type: KType): Any? {
        return sizeGenerator.process(param, annotation, clazz, type)
    }
}