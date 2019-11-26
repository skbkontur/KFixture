package ru.kontur.test.kfixture

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.api.FixtureMetaGenerator
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

@ExtendWith(FixtureParameterResolver::class)
@FixtureMetaGenerator(pathes = ["ru.kontur.spring.test.generator"])
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