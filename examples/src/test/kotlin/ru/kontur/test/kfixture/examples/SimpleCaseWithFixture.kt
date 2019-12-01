package ru.kontur.test.kfixture.examples

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.api.FixtureGeneratorMeta
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver

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