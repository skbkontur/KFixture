package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.api.ScannerSettings
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Max

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(ScannerSettings(paths = ["ru.kontur.kinfra.kfixture"]))
internal class MaxGeneratorTest {

    data class SimpleClassWithMaxField(
        @field:Max(10)
        val number: Long
    )

    @Test
    fun test(@JavaxFixture test: SimpleClassWithMaxField) {
        Assertions.assertNotNull(test)
        println(test)
    }
}