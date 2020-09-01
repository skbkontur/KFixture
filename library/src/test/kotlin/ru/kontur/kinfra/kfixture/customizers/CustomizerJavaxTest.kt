package ru.kontur.kinfra.kfixture.customizers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Customized
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
private class CustomizerJavaxTest {

    @Test
    fun `should generate`(@BusinessJavaxFixture fixture: ClassForCustomizations) {
        Assertions.assertEquals("AAAA", fixture.param2)
        Assertions.assertEquals("<<<<", fixture.param1)
        Assertions.assertEquals(fixture.param.isEmpty(), true)
    }
}

@JavaxFixture
@Customized(
    sequence = [ClassCustomizer::class, SecondClassCustomizer::class]
)
annotation class BusinessJavaxFixture