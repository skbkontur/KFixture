package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.AssertTrue

@ExtendWith(FixtureParameterResolver::class)
private class TrueJavaxTest {
    data class WrapperTrueValue(
        @field:AssertTrue
        val value: Boolean
    )

    @RepeatedTest(10)
    fun `should generate value with true`(@JavaxFixture wrapper: WrapperTrueValue) {
        assertEquals(wrapper.value, true)
    }
}