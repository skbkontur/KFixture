package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
class GenerateFloatTypesTest {

    @Test
    fun `should generate float`(@Fixture short: Short) {
    }

    @Test
    fun `should generate double`(@Fixture double: Double) {
    }
}