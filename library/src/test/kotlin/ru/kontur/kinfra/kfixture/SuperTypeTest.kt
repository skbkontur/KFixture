package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.api.ScannerSettings
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.util.*

@FixtureGeneratorMeta(ScannerSettings(paths = ["ru.kontur.kinfra"]))
annotation class Collector

@Collector
@ExtendWith(FixtureParameterResolver::class)
class SuperTypeTest {

    @Test
    fun `should generate`(@Fixture id: UUID) {
    }
}