package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Null

@ExtendWith(FixtureParameterResolver::class)
private class NullJavaxTest {

    data class NullWrapper(
        @field:Null
        val param: String?
    )

    @RepeatedTest(10)
    fun `should generate null param`(@JavaxFixture fixture: NullWrapper) {
        assertEquals(fixture.param == null, true)
    }
}