package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.AssertFalse

@ExtendWith(FixtureParameterResolver::class)
private class FalseJavaxTest {
    data class WrapperFalseValue(
        @field:AssertFalse
        val value: Boolean
    )

    @RepeatedTest(10)
    fun `should generate wrapper with false value`(@JavaxFixture wrapper: WrapperFalseValue) {
        assertEquals(wrapper.value, false)
    }
}