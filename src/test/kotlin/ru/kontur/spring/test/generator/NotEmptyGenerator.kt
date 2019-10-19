package ru.kontur.spring.test.generator

import org.junit.jupiter.api.Assertions
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

    data class SimpleClassWithMap(
        @field:NotEmpty
        val param: Map<String, String>
    )

    data class DifficultClass(
        @field:NotEmpty
        val param: String,
        val param1: SimpleClassWithCollection,
        val param2: SimpleClassWithMap
    )

    @Test
    fun test2(@Generate test: SimpleClassWithAnnotation) {
        Assertions.assertNotNull(test.param)
        assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateList(@Generate test: SimpleClassWithCollection) {
        assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateMap(@Generate test: SimpleClassWithMap) {
        assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateDifficultClass(@Generate test: DifficultClass) {
        assertTrue(test.param.isNotEmpty())
        assertTrue(test.param1.param.isNotEmpty())
        assertTrue(test.param2.param.isNotEmpty())
    }
}