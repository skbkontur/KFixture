package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.constraints.Size

@ExtendWith(FixtureParameterResolver::class)
private class SizeJavaxTest {
    data class SizeStringWrapper(
        @field:Size(min = 1, max = 5)
        val param: String
    )

    @RepeatedTest(10)
    fun `should generate string between 1 and 5`(@JavaxFixture fixture: SizeStringWrapper) {
        assertEquals(fixture.param.length in 1..5, true)
    }

    data class SizeCollectionWrapper(
        @field:Size(min = 1, max = 5)
        val param: ArrayList<String>
    )

    @RepeatedTest(10)
    fun `should generate collection between 1 and 5`(@JavaxFixture fixture: SizeCollectionWrapper) {
        assertEquals(fixture.param.size in 1..5, true)
    }

    data class SizeMapWrapper(
        @field:Size(min = 1, max = 5)
        val param: Map<String, String>
    )

    @RepeatedTest(10)
    fun `should generate map between 1 and 5`(@JavaxFixture fixture: SizeMapWrapper) {
        assertEquals(fixture.param.size in 1..5, true)
    }

    data class SizeArrayWrapper(
        @field:Size(min = 1, max = 5)
        val param: IntArray
    )
}