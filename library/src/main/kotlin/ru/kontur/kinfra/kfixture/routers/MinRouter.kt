package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.MinGenerator
import ru.kontur.kinfra.kfixture.generators.creators.*
import ru.kontur.kinfra.kfixture.generators.operators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Min
import kotlin.reflect.KClass
import kotlin.reflect.KType

class MinRouter<T> : ValidRouter<T, Min> where T : Any, T : Comparable<T> {
    private val bigDecimalMinGenerator = MinGenerator(
        BigDecimalCreator(),
        BigDecimalPlusSupplier()
    )
    private val bigIntegerMinGenerator = MinGenerator(
        BigIntegerCreator(),
        BigIntegerPlusSupplier()
    )
    private val longMinGenerator = MinGenerator(LongCreator(), LongPlusSupplier())
    private val intMinGenerator = MinGenerator(IntCreator(), IntPlusSupplier())
    private val shortMinGenerator = MinGenerator(ShortCreator(), ShortPlusSupplier())
    private val byteMinGenerator = MinGenerator(ByteCreator(), BytePlusSupplier())

    override fun process(
        param: T,
        annotation: Min,
        clazz: KClass<T>,
        type: KType
    ): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalMinGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerMinGenerator.process(param, annotation, clazz, type)
            is Byte -> byteMinGenerator.process(param, annotation, clazz, type)
            is Short -> shortMinGenerator.process(param, annotation, clazz, type)
            is Int -> intMinGenerator.process(param, annotation, clazz, type)
            is Long -> longMinGenerator.process(param, annotation, clazz, type)
            else -> {
                throw IllegalArgumentException("Max validation can't be applyed to type ${param::class.simpleName}")
            }
        }
    }
}