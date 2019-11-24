package ru.kontur.test.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.api.FixtureGenerator
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver
import java.time.LocalDate
import javax.validation.constraints.Past

@ExtendWith(FixtureParameterResolver::class)
@FixtureGenerator(value = ["ru.kontur.spring.test.generator"])
class FixtureTest {

    annotation class Custom

    data class TestData(
        @field:Custom
        val param: String,
        @field:Past
        val secondParam: LocalDate
    )

    @Test
    fun test(
        @Fixture clazz: TestData
    ) {
        assertNotNull(clazz)
    }
}