package ru.kontur.kinfra.kfixture.customizers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Customized
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
internal class CustomizerFixtureTest {

    @Test
    fun `should generate`(@BusinessFixture fixture: ClassForCustomizations) {
        assertEquals("AAAA", fixture.param2)
        assertEquals("<<<<", fixture.param1)
    }
}

@Fixture
@Customized(
    sequence = [ClassCustomizer::class, SecondClassCustomizer::class]
)
annotation class BusinessFixture