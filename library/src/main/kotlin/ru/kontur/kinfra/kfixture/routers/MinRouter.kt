package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.MinGenerator
import ru.kontur.kinfra.kfixture.generators.creators.*
import ru.kontur.kinfra.kfixture.generators.operators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Min

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

    override fun process(param: T, annotation: Min): T {
        return when (param) {
            is BigDecimal -> bigDecimalMinGenerator.process(param, annotation)
            is BigInteger -> bigIntegerMinGenerator.process(param, annotation)
            is Byte -> byteMinGenerator.process(param, annotation)
            is Short -> shortMinGenerator.process(param, annotation)
            is Int -> intMinGenerator.process(param, annotation)
            is Long -> longMinGenerator.process(param, annotation)
            else -> {
                throw IllegalArgumentException("Max validation can't be applyed to type ${param::class.simpleName}")
            }
        } as T
    }
}