package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.creators.*
import ru.kontur.kinfra.kfixture.generators.decimal.MinDecimalGenerator
import ru.kontur.kinfra.kfixture.generators.operators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.DecimalMin
import kotlin.reflect.KClass
import kotlin.reflect.KType

class MinDecimalRouter<T> : ValidRouter<T, DecimalMin> where T : Any {
    private val bigDecimalMinDecimalGenerator = MinDecimalGenerator(BigDecimalCreator(), BigDecimalPlusSupplier())
    private val bigIntegerMinDecimalGenerator = MinDecimalGenerator(BigIntegerCreator(), BigIntegerPlusSupplier())
    private val longMinDecimalGenerator = MinDecimalGenerator(LongCreator(), LongPlusSupplier())
    private val intMinDecimalGenerator = MinDecimalGenerator(IntCreator(), IntPlusSupplier())
    private val shortMinDecimalGenerator = MinDecimalGenerator(ShortCreator(), ShortPlusSupplier())
    private val byteMinDecimalGenerator = MinDecimalGenerator(ByteCreator(), BytePlusSupplier())

    override fun process(param: T, annotation: DecimalMin, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalMinDecimalGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerMinDecimalGenerator.process(param, annotation, clazz, type)
            is Byte -> byteMinDecimalGenerator.process(param, annotation, clazz, type)
            is Short -> shortMinDecimalGenerator.process(param, annotation, clazz, type)
            is Int -> intMinDecimalGenerator.process(param, annotation, clazz, type)
            is Long -> longMinDecimalGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}