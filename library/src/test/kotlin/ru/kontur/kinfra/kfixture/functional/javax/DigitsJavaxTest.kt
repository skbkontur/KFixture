package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Digits

@ExtendWith(FixtureParameterResolver::class)
private class DigitsJavaxTest {
    data class BigDecimalWrapper(
        @field:Digits(integer = 1, fraction = 1)
        val value: BigDecimal
    )

    @RepeatedTest(10)
    fun `should generate big decimal digits`(@JavaxFixture wrapper: BigDecimalWrapper) {
        assertEquals("1.1", wrapper.value.toString())
    }

    data class BigIntegerWrapper(
        @field:Digits(integer = 1, fraction = 0)
        val value: BigInteger
    )

    @RepeatedTest(10)
    fun `should generate big integer digits`(@JavaxFixture wrapper: BigIntegerWrapper) {
        assertEquals("1", wrapper.value.toString())
    }

    data class LongWrapper(
        @field:Digits(integer = 1, fraction = 0)
        val value: Long
    )

    @RepeatedTest(10)
    fun `should generate long digits`(@JavaxFixture wrapper: LongWrapper) {
        assertEquals(1, wrapper.value)
    }

    data class IntWrapper(
        @field:Digits(integer = 1, fraction = 0)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate int digits`(@JavaxFixture wrapper: IntWrapper) {
        assertEquals(1, wrapper.value)
    }

    data class ShortWrapper(
        @field:Digits(integer = 1, fraction = 0)
        val value: Short
    )

    @RepeatedTest(10)
    fun `should generate short digits`(@JavaxFixture wrapper: ShortWrapper) {
        assertEquals(1, wrapper.value)
    }

    data class ByteWrapper(
        @field:Digits(integer = 1, fraction = 0)
        val value: Byte
    )

    @RepeatedTest(10)
    fun `should generate byte digits`(@JavaxFixture wrapper: ByteWrapper) {
        assertEquals(1, wrapper.value)
    }
}