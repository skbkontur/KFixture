package ru.kontur.test.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.api.FixtureGenerator
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@FixtureGenerator(value = ["ru.kontur.spring.test.generator"])
class InterfaceTest {

    interface TestInterface {
        val param: String
    }

    class SimpleTestInstance(override val param: String) : TestInterface

    data class TestData(
        val field: TestInterface
    )

    @Test
    fun test(@Fixture data: TestData) {
    }
}