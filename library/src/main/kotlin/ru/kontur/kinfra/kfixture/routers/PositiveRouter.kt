package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.PositiveGenerator
import ru.kontur.kinfra.kfixture.generators.creators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Positive
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class PositiveRouter<T> : ValidRouter<T, Positive> where T : Any {
    private val bigDecimalPositiveGenerator = PositiveGenerator(BigDecimalCreator())
    private val bigIntegerPositiveGenerator = PositiveGenerator(BigIntegerCreator())
    private val bytePositiveGenerator = PositiveGenerator(ByteCreator())
    private val shortPositiveGenerator = PositiveGenerator(ShortCreator())
    private val intPositiveGenerator = PositiveGenerator(IntCreator())
    private val longPositiveGenerator = PositiveGenerator(LongCreator())
    private val floatPositiveGenerator = PositiveGenerator(FloatCreator())

    override fun process(param: T, annotation: Positive, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalPositiveGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerPositiveGenerator.process(param, annotation, clazz, type)
            is Byte -> bytePositiveGenerator.process(param, annotation, clazz, type)
            is Short -> shortPositiveGenerator.process(param, annotation, clazz, type)
            is Int -> intPositiveGenerator.process(param, annotation, clazz, type)
            is Long -> longPositiveGenerator.process(param, annotation, clazz, type)
            is Float -> floatPositiveGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}