package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.NotEmpty

@ExtendWith(FixtureParameterResolver::class)
private class NotEmptyJavaxTest {

    data class NotEmptyWrapper(
        @field:NotEmpty
        val param: String
    )

    @RepeatedTest(10)
    fun `should generate not empty string`(@JavaxFixture wrapper: NotEmptyWrapper) {
        assertEquals(wrapper.param.isNotEmpty(), true)
    }
}