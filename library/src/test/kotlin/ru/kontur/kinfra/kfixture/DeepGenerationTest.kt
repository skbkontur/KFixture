package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.data.objects.random1.RandomDataStructure
import ru.kontur.kinfra.kfixture.data.objects.random2.RandomDataStructure1
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
class DeepGenerationTest {
    @RepeatedTest(10)
    fun `should generate big random data`(@Fixture fixture: RandomDataStructure) {
    }

    @RepeatedTest(10)
    fun `should generate big random data structure1`(@Fixture fixture: RandomDataStructure1) {
    }
}