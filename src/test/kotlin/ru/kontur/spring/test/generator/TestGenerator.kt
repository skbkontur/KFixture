package ru.kontur.spring.test.generator

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.spring.test.generator.annotations.Generate
import ru.kontur.spring.test.generator.resolver.GenerateParameterResolver
import javax.validation.constraints.NotEmpty

@ExtendWith(GenerateParameterResolver::class)
class TestGenerator {

    data class SimpleClass(
        val param: String
    )

    @Test
    fun test(@Generate test: SimpleClass) {
        assertNotNull(test.param)
    }
}