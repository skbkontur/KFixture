package ru.kontur.spring.test.generator

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.spring.test.generator.annotations.Fixture
import ru.kontur.spring.test.generator.api.SpringTestDataGenerator
import ru.kontur.spring.test.generator.resolver.FixtureParameterResolver
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

@ExtendWith(FixtureParameterResolver::class)
@SpringTestDataGenerator(value = "ru.kontur.spring.test.generator")
class DateTests {

    @RepeatedTest(100)
    fun localDate(@Fixture localDate: LocalDate) {
    }

    @RepeatedTest(100)
    fun date(@Fixture date: Date) {
    }

    @RepeatedTest(100)
    fun zoneOffset(@Fixture zoneOffset: ZoneOffset) {
    }
}