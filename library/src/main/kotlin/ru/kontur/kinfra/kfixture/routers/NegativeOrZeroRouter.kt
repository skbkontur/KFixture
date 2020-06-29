package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.NegativeOrZeroGenerator
import ru.kontur.kinfra.kfixture.generators.creators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.NegativeOrZero
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class NegativeOrZeroRouter<T> : ValidRouter<T, NegativeOrZero> where T : Any {
    private val bigDecimalNegativeOrZeroGenerator = NegativeOrZeroGenerator(BigDecimalCreator())
    private val bigIntegerNegativeOrZeroGenerator = NegativeOrZeroGenerator(BigIntegerCreator())
    private val byteNegativeOrZeroGenerator = NegativeOrZeroGenerator(ByteCreator())
    private val shortNegativeOrZeroGenerator = NegativeOrZeroGenerator(ShortCreator())
    private val intNegativeOrZeroGenerator = NegativeOrZeroGenerator(IntCreator())
    private val longNegativeOrZeroGenerator = NegativeOrZeroGenerator(LongCreator())
    private val floatNegativeOrZeroGenerator = NegativeOrZeroGenerator(FloatCreator())

    override fun process(param: T, annotation: NegativeOrZero, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            is Byte -> byteNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            is Short -> shortNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            is Int -> intNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            is Long -> longNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            is Float -> floatNegativeOrZeroGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}