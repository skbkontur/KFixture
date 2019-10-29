package ru.kontur.spring.test.generator.exceptions

import kotlin.reflect.KClass

class NotValidateAnnotationException(clazz: KClass<out Any>) :
    Exception("class ${clazz.simpleName} is not validate annotation")