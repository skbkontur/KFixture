package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.creators.*
import ru.kontur.kinfra.kfixture.generators.decimal.MaxDecimalGenerator
import ru.kontur.kinfra.kfixture.generators.operators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.DecimalMax
import kotlin.reflect.KClass
import kotlin.reflect.KType

class MaxDecimalRouter<T> : ValidRouter<T, DecimalMax> where T : Any {
    private val bigDecimalMaxDecimalGenerator = MaxDecimalGenerator(BigDecimalCreator(), BigDecimalMinusSupplier())
    private val bigIntegerMaxDecimalGenerator = MaxDecimalGenerator(BigIntegerCreator(), BigIntegerMinusSupplier())
    private val longMaxDecimalGenerator = MaxDecimalGenerator(LongCreator(), LongMinusSupplier())
    private val intMaxDecimalGenerator = MaxDecimalGenerator(IntCreator(), IntMinusSupplier())
    private val shortMaxDecimalGenerator = MaxDecimalGenerator(ShortCreator(), ShortMinusSupplier())
    private val byteMaxDecimalGenerator = MaxDecimalGenerator(ByteCreator(), ByteMinusSupplier())

    override fun process(param: T, annotation: DecimalMax, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalMaxDecimalGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerMaxDecimalGenerator.process(param, annotation, clazz, type)
            is Byte -> byteMaxDecimalGenerator.process(param, annotation, clazz, type)
            is Short -> shortMaxDecimalGenerator.process(param, annotation, clazz, type)
            is Int -> intMaxDecimalGenerator.process(param, annotation, clazz, type)
            is Long -> longMaxDecimalGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}