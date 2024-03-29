package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.data.TestModel
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
class TestGenerator {

    data class SimpleClass(
        val param: String
    )

    enum class TestEnum {
        EN
    }

    data class ClassWithEmum(
        val type: TestEnum
    )

    @Test
    fun test(@JavaxFixture test: SimpleClass) {
        assertNotNull(test.param)
    }

    @Test
    fun testWithEnum(@JavaxFixture data: ClassWithEmum) {
        assertNotNull(data)
    }

    @Test
    fun testWithTestData1(@JavaxFixture data: TestModel) {
        assertNotNull(data)
    }

    @Test
    fun `should generate with fixture`(@Fixture data: TestModel) {
        assertNotNull(data)
    }

    @Test
    fun `should generate with old javax Fixture`(@JavaxFixture data: TestModel) {
        assertNotNull(data)
    }
}