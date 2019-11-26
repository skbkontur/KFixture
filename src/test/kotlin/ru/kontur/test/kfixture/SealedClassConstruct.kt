package ru.kontur.test.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver

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
class SealedClassConstruct {
    data class TestData(
        val sealed: TestSealed
    )

    @Test
    fun `should generate clazz with sealed field`(@Fixture data: TestData) {
        assertNotNull(data)
    }
}