package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.NotNull

@ExtendWith(FixtureParameterResolver::class)
private class NotNullJavaxTest {

    data class NotNullWrapper(
        @field:NotNull
        val param: String?
    )

    @RepeatedTest(10)
    fun `should generate not null value`(@JavaxFixture fixture: NotNullWrapper) {
        assertEquals(fixture.param != null, true)
    }
}