package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.kinfra.kfixture"])
class RecursionTest {

    data class RecursionData(
        val someParam: String,
        val recursionParam: RecursionData? = null
    )

    @Test
    fun testGenerate(
        @JavaxFixture data: RecursionData
    ) {
        assertNotNull(data)
        assertNotNull(data.someParam)
        assertNull(data.recursionParam)
    }
}