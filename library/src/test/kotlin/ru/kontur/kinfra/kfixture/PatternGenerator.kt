package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.constraints.Pattern

@ExtendWith(FixtureParameterResolver::class)
@Disabled
class PatternGenerator {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    private val regex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*\$"

    data class TestData(
        @field:Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]?[-\\s\\./0-9]*\$")
        val phoneNumber: String
    )

    @Test
    fun generateValidData(@JavaxFixture data: TestData) {
        val results = validator.validate(data)
        assertEquals(0, results.size)
    }
}