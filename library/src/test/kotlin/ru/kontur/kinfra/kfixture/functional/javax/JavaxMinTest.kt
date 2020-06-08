package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Min

@ExtendWith(FixtureParameterResolver::class)
internal class JavaxMinTest {

    data class MinWrapper(
        @field:Min(5000)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate more than 5000`(@JavaxFixture minWrapper: MinWrapper) {
        assertEquals(minWrapper.value > 5000, true)
    }

    data class MinWrapperMoreZero(
        @field:Min(0)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate more than 0`(@JavaxFixture minWrapperMoreZero: MinWrapperMoreZero) {
        assertEquals(minWrapperMoreZero.value > 0, true)
    }

    data class MinWrapperMoreNegative(
        @field:Min(-10000)
        val value: Int
    )

    @RepeatedTest(10)
    fun `should generate more than negative`(@JavaxFixture minWrapperMoreNegative: MinWrapperMoreNegative) {
        assertEquals(minWrapperMoreNegative.value > -10000, true)
    }
}