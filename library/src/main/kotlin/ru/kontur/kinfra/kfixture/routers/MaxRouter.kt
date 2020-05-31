package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
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
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
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

    override fun process(
        param: T,
        annotation: Max,
        clazz: KClass<*>,
        type: KType
    ): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalMaxGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerMaxGenerator.process(param, annotation, clazz, type)
            is Byte -> byteMaxGenerator.process(param, annotation, clazz, type)
            is Short -> shortMaxGenerator.process(param, annotation, clazz, type)
            is Int -> intMaxGenerator.process(param, annotation, clazz, type)
            is Long -> longMaxGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}