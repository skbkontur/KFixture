package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
class ListGenerateTest {

    data class TestClass(
        val param: String
    )

    @Test
    fun `should generate list`(@Fixture list: List<TestClass>) {
    }
}