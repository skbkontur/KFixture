package ru.kontur.kinfra.kfixture.examples

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.test.kfixture"])
class SimpleCaseWithFixture {

    data class TestData(
        val param: String,
        val param2: String
    )

    @Test
    fun generateData(@Fixture data: TestData) {
        assertNotNull(data)
    }
}