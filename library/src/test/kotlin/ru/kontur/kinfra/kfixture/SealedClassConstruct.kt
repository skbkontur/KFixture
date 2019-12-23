package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

sealed class TestSealed(
    val param: String
)

class ConcreteClass(
    param: String,
    val secondParam: String
) : TestSealed(
    param
)

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.kinfra.kfixture"])
class SealedClassConstruct {
    data class TestData(
        val sealed: TestSealed
    )

    @Test
    fun `should generate clazz with sealed field`(@Fixture data: TestData) {
        assertNotNull(data)
    }
}