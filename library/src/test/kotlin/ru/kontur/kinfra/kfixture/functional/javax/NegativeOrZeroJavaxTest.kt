package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.NegativeOrZero

@ExtendWith(FixtureParameterResolver::class)
private class NegativeOrZeroJavaxTest {
    data class BigDecimalWrapper(
        @field:NegativeOrZero
        val value: BigDecimal
    )

    @RepeatedTest(10)
    fun `should generate big decimal negative or zero`(@JavaxFixture wrapper: BigDecimalWrapper) {
        Assertions.assertEquals(wrapper.value <= BigDecimal.ZERO, true)
    }

    data class BigIntegerWrapper(
        @field:NegativeOrZero
        val value: BigInteger
    )

    @RepeatedTest(10)
    fun `should generate big integer negative or zero`(@JavaxFixture wrapper: BigIntegerWrapper) {
        Assertions.assertEquals(wrapper.value <= BigInteger.ZERO, true)
    }

    data class LongWrapper(
        @field:NegativeOrZero
        val value: Long
    )

    @RepeatedTest(10)
    fun `should generate long negative or zero`(@JavaxFixture wrapper: LongWrapper) {
        Assertions.assertEquals(wrapper.value <= 0, true)
    }

    data class IntWrapper(
        @field:NegativeOrZero
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate int negative or zero`(@JavaxFixture wrapper: IntWrapper) {
        Assertions.assertEquals(wrapper.value <= 0, true)
    }

    data class ShortWrapper(
        @field:NegativeOrZero
        val value: Short
    )

    @RepeatedTest(10)
    fun `should generate short negative or zero`(@JavaxFixture wrapper: ShortWrapper) {
        Assertions.assertEquals(wrapper.value <= 0, true)
    }

    data class ByteWrapper(
        @field:NegativeOrZero
        val value: Byte
    )

    @RepeatedTest(10)
    fun `should generate byte negative or zero`(@JavaxFixture wrapper: ByteWrapper) {
        Assertions.assertEquals(wrapper.value <= 0, true)
    }
}