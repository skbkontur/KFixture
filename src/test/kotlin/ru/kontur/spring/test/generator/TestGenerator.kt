package ru.kontur.spring.test.generator

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.spring.test.generator.annotations.Generate
import ru.kontur.spring.test.generator.data.TestModel
import ru.kontur.spring.test.generator.resolver.GenerateParameterResolver

@ExtendWith(GenerateParameterResolver::class)
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
    fun test(@Generate test: SimpleClass) {
        assertNotNull(test.param)
    }

    @Test
    fun testWithEnum(@Generate data: ClassWithEmum) {
        assertNotNull(data)
    }

    @Test
    fun testWithTestData1(@Generate data: TestModel) {
        assertNotNull(data)
    }
}