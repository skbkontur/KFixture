package ru.kontur.kinfra.kfixture.generators.operators

import java.math.BigDecimal
import java.math.BigInteger

class BigIntegerMinusSupplier : MinusSupplier<BigInteger> {
    override fun minus(first: BigInteger, other: BigInteger): BigInteger {
        return first.minus(other)
    }
}

class BigDecimalMinusSupplier : MinusSupplier<BigDecimal> {
    override fun minus(first: BigDecimal, other: BigDecimal): BigDecimal {
        return first.minus(other)
    }
}

class IntMinusSupplier : MinusSupplier<Int> {
    override fun minus(first: Int, other: Int): Int {
        return first - other
    }
}

class ShortMinusSupplier : MinusSupplier<Short> {
    override fun minus(first: Short, other: Short): Short {
        return first.minus(other).toShort()
    }
}

class ByteMinusSupplier : MinusSupplier<Byte> {
    override fun minus(first: Byte, other: Byte): Byte {
        return first.minus(other).toByte()
    }
}

class LongMinusSupplier : MinusSupplier<Long> {
    override fun minus(first: Long, other: Long): Long {
        return first.minus(other)
    }
}