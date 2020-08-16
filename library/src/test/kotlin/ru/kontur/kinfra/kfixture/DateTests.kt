package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.kinfra.kfixture"])
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