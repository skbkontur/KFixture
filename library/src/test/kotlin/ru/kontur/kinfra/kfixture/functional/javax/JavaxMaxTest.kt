package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Max

@ExtendWith(FixtureParameterResolver::class)
private class JavaxMaxTest {
    data class MaxWrapper(
        @field:Max(5000)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate less than 500`(@JavaxFixture maxWrapper: MaxWrapper) {
        assertEquals(maxWrapper.value < 5000, true)
    }

    data class MaxWrapperLessZero(
        @field:Max(0)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate less than 0`(@JavaxFixture maxWrapperLessZero: MaxWrapperLessZero) {
        assertEquals(maxWrapperLessZero.value < 0, true)
    }

    data class MaxWrapperLessNegative(
        @field:Max(-10000)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate less than negative`(@JavaxFixture maxWrapperLessNegative: MaxWrapperLessNegative) {
        assertEquals(maxWrapperLessNegative.value < -10000, true)
    }
}