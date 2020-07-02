package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import ru.kontur.sealed.TestSealed

@ExtendWith(FixtureParameterResolver::class)
class SealedClassConstruct {
    data class TestData(
        val sealed: TestSealed
    )

    @Test
    fun `should generate clazz with sealed field`(@Fixture data: TestData) {
        assertNotNull(data)
    }
}