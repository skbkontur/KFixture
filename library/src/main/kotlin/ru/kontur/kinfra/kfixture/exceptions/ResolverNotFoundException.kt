package ru.kontur.kinfra.kfixture.exceptions

import kotlin.reflect.KClass

class ResolverNotFoundException(val clazz: KClass<out Any>) :
    Exception(
        "resolver for clazz ${clazz.simpleName} was not found, please annotate your resolver with ValidateFor annotation"
    )