package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.NotBlank

@ExtendWith(FixtureParameterResolver::class)
private class NotBlankJavaxTest {
    data class NotBlankWrapper(
        @field:NotBlank
        val param: String
    )

    @RepeatedTest(10)
    fun `should generate not blank string`(@JavaxFixture fixture: NotBlankWrapper) {
        assertEquals(fixture.param.isNotBlank(), true)
    }
}