package ru.kontur.spring.test.generator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.spring.test.generator.annotations.Generate
import ru.kontur.spring.test.generator.resolver.GenerateParameterResolver
import javax.validation.constraints.NotEmpty

@ExtendWith(GenerateParameterResolver::class)
class NotEmptyGenerator {

    data class SimpleClassWithAnnotation(
        @field:NotEmpty
        val param: String
    )

    data class SimpleClassWithCollection(
        @field:NotEmpty
        val param: List<String>
    )

    @Test
    fun test2(@Generate test: TestGenerator.SimpleClassWithAnnotation) {
        Assertions.assertNotNull(test.param)
        Assertions.assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateList(@Generate test: SimpleClassWithCollection) {
        assertTrue(test.param.isNotEmpty())
    }
}