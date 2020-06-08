package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.NegativeGenerator
import ru.kontur.kinfra.kfixture.generators.creators.*
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Negative
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class NegativeRouter<T> : ValidRouter<T, Negative> where T : Any {
    private val bigDecimalNegativeGenerator = NegativeGenerator(BigDecimalCreator())
    private val bigIntegerNegativeGenerator = NegativeGenerator(BigIntegerCreator())
    private val byteNegativeGenerator = NegativeGenerator(ByteCreator())
    private val shortNegativeGenerator = NegativeGenerator(ShortCreator())
    private val intNegativeGenerator = NegativeGenerator(IntCreator())
    private val longNegativeGenerator = NegativeGenerator(LongCreator())
    private val floatNegativeGenerator = NegativeGenerator(FloatCreator())

    override fun process(param: T, annotation: Negative, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is BigDecimal -> bigDecimalNegativeGenerator.process(param, annotation, clazz, type)
            is BigInteger -> bigIntegerNegativeGenerator.process(param, annotation, clazz, type)
            is Byte -> byteNegativeGenerator.process(param, annotation, clazz, type)
            is Short -> shortNegativeGenerator.process(param, annotation, clazz, type)
            is Int -> intNegativeGenerator.process(param, annotation, clazz, type)
            is Long -> longNegativeGenerator.process(param, annotation, clazz, type)
            is Float -> floatNegativeGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}