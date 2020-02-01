package ru.kontur.kinfra.kfixture.generators.operators

import java.math.BigDecimal
import java.math.BigInteger

class BigDecimalPlusSupplier : PlusSupplier<BigDecimal> {
    override fun plus(first: BigDecimal, second: BigDecimal): BigDecimal {
        return first.plus(second)
    }
}

class BigIntegerPlusSupplier : PlusSupplier<BigInteger> {
    override fun plus(first: BigInteger, second: BigInteger): BigInteger {
        return first.plus(second)
    }
}

class LongPlusSupplier : PlusSupplier<Long> {
    override fun plus(first: Long, second: Long): Long {
        return first + second
    }
}

class IntPlusSupplier : PlusSupplier<Int> {
    override fun plus(first: Int, second: Int): Int {
        return first + second
    }
}

class ShortPlusSupplier : PlusSupplier<Short> {
    override fun plus(first: Short, second: Short): Short {
        return first.plus(second).toShort()
    }
}

class BytePlusSupplier : PlusSupplier<Byte> {
    override fun plus(first: Byte, second: Byte): Byte {
        return first.plus(second).toByte()
    }
}