package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Pattern

@ExtendWith(FixtureParameterResolver::class)
private class PatternJavaxTest {
    data class PatternWrapper(
        @field:Pattern(regexp = "\\d{10}")
        val param: String
    )

    @RepeatedTest(10)
    fun `should generate digits`(@JavaxFixture fixture: PatternWrapper) {
        assertDoesNotThrow {
            fixture.param.toBigInteger()
        }
    }
}