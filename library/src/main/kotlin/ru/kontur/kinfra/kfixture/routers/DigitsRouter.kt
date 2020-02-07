package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.DigitsGenerator
import ru.kontur.kinfra.kfixture.generators.digits.BigDecimalDigitsCreator
import ru.kontur.kinfra.kfixture.generators.digits.BigIntegerDigitsCreator
import ru.kontur.kinfra.kfixture.generators.digits.ByteDigitsCreator
import ru.kontur.kinfra.kfixture.generators.digits.IntDigitsCreator
import ru.kontur.kinfra.kfixture.generators.digits.LongDigitsCreator
import ru.kontur.kinfra.kfixture.generators.digits.ShortDigitsCreator
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Digits
import kotlin.reflect.KClass
import kotlin.reflect.KType

class DigitsRouter<T> : ValidRouter<T, Digits> where T : Comparable<T>, T : Any {
    private val bigDecimalDigitsGenerator = DigitsGenerator(BigDecimalDigitsCreator())
    private val bigIntegerDigitsCreator = DigitsGenerator(BigIntegerDigitsCreator())
    private val longDigitsCreator = DigitsGenerator(LongDigitsCreator())
    private val intDigitsCreator = DigitsGenerator(IntDigitsCreator())
    private val shortDigitsCreator = DigitsGenerator(ShortDigitsCreator())
    private val byteDigitsCreator = DigitsGenerator(ByteDigitsCreator())

    override fun process(param: T, annotation: Digits, clazz: KClass<T>, type: KType): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalDigitsGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerDigitsCreator.process(param, annotation, clazz, type)
            is Long -> longDigitsCreator.process(param, annotation, clazz, type)
            is Int -> intDigitsCreator.process(param, annotation, clazz, type)
            is Short -> shortDigitsCreator.process(param, annotation, clazz, type)
            is Byte -> byteDigitsCreator.process(param, annotation, clazz, type)
            else -> {
                throw IllegalArgumentException("Max validation can't be applyed to type ${param::class.simpleName}")
            }
        }
    }
}