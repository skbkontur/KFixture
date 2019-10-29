package ru.kontur.spring.test.generator.exceptions

import kotlin.reflect.KClass

class NotResolverException(clazz: KClass<out Any>) :
    Exception("Resolver was not implemented for ${clazz.simpleName}, please use validationParamResolver")