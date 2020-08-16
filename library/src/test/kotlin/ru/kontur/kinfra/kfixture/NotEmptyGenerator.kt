package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.NotEmpty

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.kinfra.kfixture"])
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
    fun test2(@JavaxFixture test: SimpleClassWithAnnotation, @JavaxFixture test2: SimpleClassWithAnnotation) {
        Assertions.assertNotNull(test.param)
        assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateList(@JavaxFixture test: SimpleClassWithCollection) {
        assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateMap(@JavaxFixture test: SimpleClassWithMap) {
        assertTrue(test.param.isNotEmpty())
    }

    @Test
    fun generateDifficultClass(@JavaxFixture test: DifficultClass) {
        assertTrue(test.param.isNotEmpty())
        assertTrue(test.param1.param.isNotEmpty())
        assertTrue(test.param2.param.isNotEmpty())
    }
}