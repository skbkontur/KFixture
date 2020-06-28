package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Positive

@ExtendWith(FixtureParameterResolver::class)
private class PositiveJavaxTest {
    data class BigDecimalWrapper(
        @field:Positive
        val value: BigDecimal
    )

    @RepeatedTest(10)
    fun `should generate big decimal positive`(@JavaxFixture wrapper: BigDecimalWrapper) {
        assertEquals(wrapper.value > BigDecimal.ZERO, true)
    }

    data class BigIntegerWrapper(
        @field:Positive
        val value: BigInteger
    )

    @RepeatedTest(10)
    fun `should generate big integer positive`(@JavaxFixture wrapper: BigIntegerWrapper) {
        assertEquals(wrapper.value > BigInteger.ZERO, true)
    }

    data class LongWrapper(
        @field:Positive
        val value: Long
    )

    @RepeatedTest(10)
    fun `should generate long positive`(@JavaxFixture wrapper: LongWrapper) {
        assertEquals(wrapper.value > 0, true)
    }

    data class IntWrapper(
        @field:Positive
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate int positive`(@JavaxFixture wrapper: IntWrapper) {
        assertEquals(wrapper.value > 0, true)
    }

    data class ShortWrapper(
        @field:Positive
        val value: Short
    )

    @RepeatedTest(10)
    fun `should generate short positive`(@JavaxFixture wrapper: ShortWrapper) {
        assertEquals(wrapper.value > 0, true)
    }

    data class ByteWrapper(
        @field:Positive
        val value: Byte
    )

    @RepeatedTest(10)
    fun `should generate byte positive`(@JavaxFixture wrapper: ByteWrapper) {
        assertEquals(wrapper.value > 0, true)
    }
}