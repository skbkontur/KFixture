package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
private class SetDataGeneration {
    data class DataClassWithSet(
        val param: Set<String>
    )

    @Test
    fun `should generate data with set`(@Fixture fixture: DataClassWithSet) {
    }
}