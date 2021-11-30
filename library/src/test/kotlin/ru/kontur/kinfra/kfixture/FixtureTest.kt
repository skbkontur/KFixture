package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.api.ScannerSettings
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.time.LocalDate
import javax.validation.constraints.Past

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(ScannerSettings(paths = ["ru.kontur.kinfra.kfixture"]))
class FixtureTest {

    annotation class Custom

    data class TestData(
        @field:Custom
        val param: String,
        @field:Past
        val secondParam: LocalDate
    )

    @Test
    fun test(
        @Fixture clazz: TestData
    ) {
        assertNotNull(clazz)
    }
}