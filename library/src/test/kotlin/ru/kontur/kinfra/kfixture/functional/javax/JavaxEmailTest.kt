package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Email

@ExtendWith(FixtureParameterResolver::class)
private class JavaxEmailTest {
    data class WrapperEmailValue(
        @field:Email
        val value: String
    )

    @RepeatedTest(10)
    fun `should generate email`(@JavaxFixture wrapper: WrapperEmailValue) {
        assertEquals(wrapper.value.contains("@gmail.com"), true)
    }
}