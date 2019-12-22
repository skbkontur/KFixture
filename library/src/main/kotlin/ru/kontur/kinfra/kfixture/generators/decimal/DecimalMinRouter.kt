package ru.kontur.kinfra.kfixture.generators.decimal

import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

class DecimalMinRouter {

    fun <T : Any> process(param: T?, clazz: KClass<T>, decimalMin: DecimalMin): Any {
        return when {
            param == null && clazz.isSubclassOf(BigDecimal::class) || param is BigDecimal -> {
                return MinBigDecimalGenerator().process(param as BigDecimal, decimalMin)
            }
            else -> {
                throw IllegalArgumentException("Can't find such case in decimalMin generation")
            }
        }
    }
}