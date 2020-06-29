package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.math.BigDecimal
import java.math.BigInteger
import javax.validation.constraints.Negative

@ExtendWith(FixtureParameterResolver::class)
private class NegativeJavaxTest {
    data class BigDecimalWrapper(
        @field:Negative
        val value: BigDecimal
    )

    @RepeatedTest(10)
    fun `should generate big decimal negative`(@JavaxFixture wrapper: BigDecimalWrapper) {
        Assertions.assertEquals(wrapper.value < BigDecimal.ZERO, true)
    }

    data class BigIntegerWrapper(
        @field:Negative
        val value: BigInteger
    )

    @RepeatedTest(10)
    fun `should generate big integer negative`(@JavaxFixture wrapper: BigIntegerWrapper) {
        Assertions.assertEquals(wrapper.value < BigInteger.ZERO, true)
    }

    data class LongWrapper(
        @field:Negative
        val value: Long
    )

    @RepeatedTest(10)
    fun `should generate long negative`(@JavaxFixture wrapper: LongWrapper) {
        Assertions.assertEquals(wrapper.value < 0, true)
    }

    data class IntWrapper(
        @field:Negative
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate int negative`(@JavaxFixture wrapper: IntWrapper) {
        Assertions.assertEquals(wrapper.value < 0, true)
    }

    data class ShortWrapper(
        @field:Negative
        val value: Short
    )

    @RepeatedTest(10)
    fun `should generate short negative`(@JavaxFixture wrapper: ShortWrapper) {
        Assertions.assertEquals(wrapper.value < 0, true)
    }

    data class ByteWrapper(
        @field:Negative
        val value: Byte
    )

    @RepeatedTest(10)
    fun `should generate byte negative`(@JavaxFixture wrapper: ByteWrapper) {
        Assertions.assertEquals(wrapper.value < 0, true)
    }
}