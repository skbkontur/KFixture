package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.Past

@ExtendWith(FixtureParameterResolver::class)
private class PastJavaxTest {
    data class DateWrapper(
        @field:Past
        val param: Date
    )

    @RepeatedTest(10)
    fun `should generate date in past`(@JavaxFixture fixture: DateWrapper) {
        Assertions.assertEquals(fixture.param < Date.from(Instant.now()), true)
    }


    data class CalendarWrapper(
        @field:Past
        val param: Calendar
    )

    @RepeatedTest(10)
    fun `should generate calendar in past`(@JavaxFixture fixture: CalendarWrapper) {
        Assertions.assertEquals(fixture.param < Calendar.getInstance().also {
            it.time = Date.from(Instant.now())
        }, true)
    }

    data class InstantWrapper(
        @field:Past
        val param: Instant
    )

    @RepeatedTest(10)
    fun `should generate instant in past`(@JavaxFixture fixture: InstantWrapper) {
        Assertions.assertEquals(fixture.param < Instant.now(), true)
    }

    data class LocalDateWrapper(
        @field:Past
        val param: LocalDate
    )

    @RepeatedTest(10)
    fun `should generate local date in past`(@JavaxFixture fixture: LocalDateWrapper) {
        Assertions.assertEquals(fixture.param < LocalDate.now(), true)
    }

    data class LocalDateTimeWrapper(
        @field:Past
        val param: LocalDateTime
    )

    @RepeatedTest(10)
    fun `should generate local date time in past`(@JavaxFixture fixture: LocalDateTimeWrapper) {
        Assertions.assertEquals(fixture.param < LocalDateTime.now(), true)
    }

    data class LocalTimeWrapper(
        @field:Past
        val param: LocalTime
    )

    @RepeatedTest(10)
    fun `should generate local time in past`(@JavaxFixture fixture: LocalTimeWrapper) {
        Assertions.assertEquals(fixture.param < LocalTime.now(), true)
    }

    data class MonthDayWrapper(
        @field:Past
        val param: MonthDay
    )

    @RepeatedTest(10)
    fun `should generate month day in past`(@JavaxFixture fixture: MonthDayWrapper) {
        Assertions.assertEquals(fixture.param < MonthDay.now(), true)
    }

    data class OffsetDateTimeWrapper(
        @field:Past
        val param: OffsetDateTime
    )

    @RepeatedTest(10)
    fun `should generate offset date time in past`(@JavaxFixture fixture: OffsetDateTimeWrapper) {
        Assertions.assertEquals(fixture.param < OffsetDateTime.now(), true)
    }

    data class OffsetTimeWrapper(
        @field:Past
        val param: OffsetTime
    )

    @RepeatedTest(10)
    fun `should generate offset time in past`(@JavaxFixture fixture: OffsetTimeWrapper) {
        Assertions.assertEquals(fixture.param < OffsetTime.now(), true)
    }

    data class YearWrapper(
        @field:Past
        val param: Year
    )

    @RepeatedTest(10)
    fun `should generate year in past`(@JavaxFixture fixture: YearWrapper) {
        Assertions.assertEquals(fixture.param < Year.now(), true)
    }

    data class YearMonthWrapper(
        @field:Past
        val param: YearMonth
    )

    @RepeatedTest(10)
    fun `should generate year month in past`(@JavaxFixture fixture: YearMonthWrapper) {
        Assertions.assertEquals(fixture.param < YearMonth.now(), true)
    }

    data class ZonedDateTimeWrapper(
        @field:Past
        val param: ZonedDateTime
    )

    @RepeatedTest(10)
    fun `should generate zoned date time in past`(@JavaxFixture fixture: ZonedDateTimeWrapper) {
        Assertions.assertEquals(fixture.param < ZonedDateTime.now(), true)
    }

    data class HijrahDateWrapper(
        @field:Past
        val param: HijrahDate
    )

    @RepeatedTest(10)
    fun `should generate hijrah date in past`(@JavaxFixture fixture: HijrahDateWrapper) {
        Assertions.assertEquals(fixture.param < HijrahDate.now(), true)
    }

    data class JapaneseDateWrapper(
        @field:Past
        val param: JapaneseDate
    )

    @RepeatedTest(10)
    fun `should generate japanese date in past`(@JavaxFixture fixture: JapaneseDateWrapper) {
        Assertions.assertEquals(fixture.param < JapaneseDate.now(), true)
    }

    data class MinguoDateWrapper(
        @field:Past
        val param: MinguoDate
    )

    @RepeatedTest(10)
    fun `should generate minguo date in past`(@JavaxFixture fixture: MinguoDateWrapper) {
        Assertions.assertEquals(fixture.param < JapaneseDate.now(), true)
    }

    data class ThaiBuddhistDateWrapper(
        @field:Past
        val param: ThaiBuddhistDate
    )

    @RepeatedTest(10)
    fun `should generate thai buddhist date in past`(@JavaxFixture fixture: ThaiBuddhistDateWrapper) {
        Assertions.assertEquals(fixture.param < ThaiBuddhistDate.now(), true)
    }
    
}