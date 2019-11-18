package ru.kontur.spring.test.generator

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.spring.test.generator.annotations.Generate
import ru.kontur.spring.test.generator.api.FixtureGenerator
import ru.kontur.spring.test.generator.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@FixtureGenerator(value = ["ru.kontur.spring.test.generator"])
class RecursionTest {

    data class RecursionData(
        val someParam: String,
        val recursionParam: RecursionData? = null
    )

    @Test
    fun testGenerate(
        @Generate data: RecursionData
    ) {
        assertNotNull(data)
        assertNotNull(data.someParam)
        assertNull(data.recursionParam)
    }
}