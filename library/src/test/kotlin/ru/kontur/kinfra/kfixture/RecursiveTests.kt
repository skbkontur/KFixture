package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
class RecursiveTests {

    data class RecursiveList(
        val test: String,
        val list: List<RecursiveList>
    )

    @Test
    fun test(@Fixture list: RecursiveList) {
    }
}