package ru.kontur.test.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver
import java.time.ZonedDateTime
import java.util.*

@ExtendWith(FixtureParameterResolver::class)
class CreateByStaticFunction {

    @Test
    fun `should create zoned date time`(@Fixture id: ZonedDateTime) {
        assertNotNull(id)
    }

    @Test
    fun `should create uuid`(@Fixture id: UUID) {
        assertNotNull(id)
    }
}