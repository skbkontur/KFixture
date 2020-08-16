package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@Fixture
internal class AnnotatedClassTests {
    data class TestDto(
        val param: String
    )

    @Test
    fun `should generate dto`(test: TestDto) {
        assertNotNull(test)
    }
}

@ExtendWith(FixtureParameterResolver::class)
@JavaxFixture
internal class AnnotatedJavaxClassTests {
    data class TestDto(
        val param: String
    )

    @Test
    fun `should generate dto`(test: TestDto) {
        assertNotNull(test)
    }
}