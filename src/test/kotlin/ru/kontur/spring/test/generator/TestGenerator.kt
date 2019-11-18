package ru.kontur.spring.test.generator

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.spring.test.generator.annotations.JavaxFixture
import ru.kontur.spring.test.generator.data.TestModel
import ru.kontur.spring.test.generator.resolver.FixtureParameterResolver

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
}