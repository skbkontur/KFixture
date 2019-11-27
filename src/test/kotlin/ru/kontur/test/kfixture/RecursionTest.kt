package ru.kontur.test.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.JavaxFixture
import ru.kontur.test.kfixture.api.FixtureGeneratorMeta
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.spring.test.generator"])
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