package ru.kontur.kinfra.kfixture.generators.operators

import java.math.BigDecimal
import java.math.BigInteger

class BigIntegerMinusSupplier : MinusSupplyer<BigInteger> {
    override fun minus(first: BigInteger, other: BigInteger): BigInteger {
        return first.minus(other)
    }
}

class BigDecimalMinusSupplier : MinusSupplyer<BigDecimal> {
    override fun minus(first: BigDecimal, other: BigDecimal): BigDecimal {
        return first.minus(other)
    }
}

class IntMinusSupplier : MinusSupplyer<Int> {
    override fun minus(first: Int, other: Int): Int {
        return first - other
    }
}

class ShortMinusSupplier : MinusSupplyer<Short> {
    override fun minus(first: Short, other: Short): Short {
        return first.minus(other).toShort()
    }
}

class ByteMinusSupplier : MinusSupplyer<Byte> {
    override fun minus(first: Byte, other: Byte): Byte {
        return first.minus(other).toByte()
    }
}

class LongMinusSupplier : MinusSupplyer<Long> {
    override fun minus(first: Long, other: Long): Long {
        return first.minus(other)
    }
}