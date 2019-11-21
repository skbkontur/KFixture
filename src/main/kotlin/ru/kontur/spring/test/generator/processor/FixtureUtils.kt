package ru.kontur.spring.test.generator.processor

import java.lang.reflect.Modifier
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.javaMethod

object FixtureUtils {

    fun getCreatorFunction(clazz: KClass<*>, annotationScanner: GeneratorAnnotationScanner): KFunction<*> {
        val searchClazz = if (clazz.isAbstract) {
            annotationScanner.getSubTypeOf(clazz).kotlin
        } else {
            clazz
        }

        val functions =
            searchClazz.functions.filter {
                val method = it.javaMethod
                if (method == null) {
                    false
                } else {
                    it.returnType::classifier.get() == clazz && Modifier.isStatic(method.modifiers)
                }
            }
        val constructors = searchClazz.constructors

        val result = (functions + constructors).toMutableList()
        result.sortBy { it.parameters.size }
        return result[0]
    }
}