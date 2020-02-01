package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.MaxGenerator
import ru.kontur.kinfra.kfixture.generators.creators.BigDecimalCreator
import ru.kontur.kinfra.kfixture.generators.creators.BigIntegerCreator
import ru.kontur.kinfra.kfixture.generators.creators.ByteCreator
import ru.kontur.kinfra.kfixture.generators.creators.IntCreator
import ru.kontur.kinfra.kfixture.generators.creators.LongCreator
import ru.kontur.kinfra.kfixture.generators.creators.ShortCreator
import ru.kontur.kinfra.kfixture.generators.operators.BigDecimalMinusSupplier
import ru.kontur.kinfra.kfixture.generators.operators.BigIntegerMinusSupplier
import ru.kontur.kinfra.kfixture.generators.operators.ByteMinusSupplier
import ru.kontur.kinfra.kfixture.generators.operators.IntMinusSupplier
import ru.kontur.kinfra.kfixture.generators.operators.LongMinusSupplier
import ru.kontur.kinfra.kfixture.generators.operators.ShortMinusSupplier
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Max

class MaxRouter<T> : ValidRouter<T, Max> where T : Any, T : Comparable<T> {
    private val bigDecimalMaxGenerator = MaxGenerator(
        BigDecimalCreator(),
        BigDecimalMinusSupplier()
    )
    private val bigIntegerMaxGenerator = MaxGenerator(
        BigIntegerCreator(), BigIntegerMinusSupplier()
    )
    private val byteMaxGenerator = MaxGenerator(ByteCreator(), ByteMinusSupplier())
    private val shortMaxGenerator = MaxGenerator(ShortCreator(), ShortMinusSupplier())
    private val intMaxGenerator = MaxGenerator(IntCreator(), IntMinusSupplier())
    private val longMaxGenerator = MaxGenerator(LongCreator(), LongMinusSupplier())

    override fun process(param: T, annotation: Max): T {
        return when (param) {
            is BigDecimal -> bigDecimalMaxGenerator.process(param, annotation)
            is BigInteger -> bigIntegerMaxGenerator.process(param, annotation)
            is Byte -> byteMaxGenerator.process(param, annotation)
            is Short -> shortMaxGenerator.process(param, annotation)
            is Int -> intMaxGenerator.process(param, annotation)
            is Long -> longMaxGenerator.process(param, annotation)
            else -> {
                throw IllegalArgumentException("Max validation can't be applyed to type ${param::class.simpleName}")
            }
        } as T
    }
}