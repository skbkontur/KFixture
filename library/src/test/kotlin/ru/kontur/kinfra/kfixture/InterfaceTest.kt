package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.api.ScannerSettings
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(ScannerSettings(paths = ["ru.kontur.kinfra.kfixture"]))
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