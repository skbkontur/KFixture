package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.FutureOrPresent

@ExtendWith(FixtureParameterResolver::class)
private class FutureOrPresentJavaxTest {
    data class DateWrapper(
        @field:FutureOrPresent
        val param: Date
    )

    @RepeatedTest(10)
    fun `should generate date in future or present`(@JavaxFixture fixture: DateWrapper) {
        Assertions.assertEquals(fixture.param >= Date.from(Instant.now()), true)
    }

    data class CalendarWrapper(
        @field:FutureOrPresent
        val param: Calendar
    )

    @RepeatedTest(10)
    fun `should generate calendar in future or present`(@JavaxFixture fixture: CalendarWrapper) {
        Assertions.assertEquals(fixture.param >= Calendar.getInstance().also {
            it.time = Date.from(Instant.now())
        }, true)
    }

    data class InstantWrapper(
        @field:FutureOrPresent
        val param: Instant
    )

    @RepeatedTest(10)
    fun `should generate instant in future or present`(@JavaxFixture fixture: InstantWrapper) {
        Assertions.assertEquals(fixture.param >= Instant.now(), true)
    }

    data class LocalDateWrapper(
        @field:FutureOrPresent
        val param: LocalDate
    )

    @RepeatedTest(10)
    fun `should generate local date in future or present`(@JavaxFixture fixture: LocalDateWrapper) {
        Assertions.assertEquals(fixture.param >= LocalDate.now(), true)
    }

    data class LocalDateTimeWrapper(
        @field:FutureOrPresent
        val param: LocalDateTime
    )

    @RepeatedTest(10)
    fun `should generate local date time in future or present`(@JavaxFixture fixture: LocalDateTimeWrapper) {
        Assertions.assertEquals(fixture.param >= LocalDateTime.now(), true)
    }

    data class LocalTimeWrapper(
        @field:FutureOrPresent
        val param: LocalTime
    )

    @RepeatedTest(10)
    @Disabled("should properly generate the borders")
    fun `should generate local time in future or present`(@JavaxFixture fixture: LocalTimeWrapper) {
        Assertions.assertEquals(fixture.param >= LocalTime.now(), true)
    }

    data class MonthDayWrapper(
        @field:FutureOrPresent
        val param: MonthDay
    )

    @RepeatedTest(10)
    fun `should generate month day in future or present`(@JavaxFixture fixture: MonthDayWrapper) {
        Assertions.assertEquals(fixture.param >= MonthDay.now(), true)
    }

    data class OffsetDateTimeWrapper(
        @field:FutureOrPresent
        val param: OffsetDateTime
    )

    @RepeatedTest(10)
    fun `should generate offset date time in future or present`(@JavaxFixture fixture: OffsetDateTimeWrapper) {
        Assertions.assertEquals(fixture.param >= OffsetDateTime.now(), true)
    }

    data class OffsetTimeWrapper(
        @field:FutureOrPresent
        val param: OffsetTime
    )

    @RepeatedTest(10)
    @Disabled("should properly generate the borders")
    fun `should generate offset time in future or present`(@JavaxFixture fixture: OffsetTimeWrapper) {
        Assertions.assertEquals(fixture.param >= OffsetTime.now(), true)
    }

    data class YearWrapper(
        @field:FutureOrPresent
        val param: Year
    )

    @RepeatedTest(10)
    fun `should generate year in future or present`(@JavaxFixture fixture: YearWrapper) {
        Assertions.assertEquals(fixture.param >= Year.now(), true)
    }

    data class YearMonthWrapper(
        @field:FutureOrPresent
        val param: YearMonth
    )

    @RepeatedTest(10)
    fun `should generate year month in future or present`(@JavaxFixture fixture: YearMonthWrapper) {
        Assertions.assertEquals(fixture.param >= YearMonth.now(), true)
    }

    data class ZonedDateTimeWrapper(
        @field:FutureOrPresent
        val param: ZonedDateTime
    )

    @RepeatedTest(10)
    fun `should generate zoned date time in future or present`(@JavaxFixture fixture: ZonedDateTimeWrapper) {
        Assertions.assertEquals(fixture.param >= ZonedDateTime.now(), true)
    }

    data class HijrahDateWrapper(
        @field:FutureOrPresent
        val param: HijrahDate
    )

    @RepeatedTest(10)
    fun `should generate hijrah date in future or present`(@JavaxFixture fixture: HijrahDateWrapper) {
        Assertions.assertEquals(fixture.param >= HijrahDate.now(), true)
    }

    data class JapaneseDateWrapper(
        @field:FutureOrPresent
        val param: JapaneseDate
    )

    @RepeatedTest(10)
    fun `should generate japanese date in future or present`(@JavaxFixture fixture: JapaneseDateWrapper) {
        Assertions.assertEquals(fixture.param >= JapaneseDate.now(), true)
    }

    data class MinguoDateWrapper(
        @field:FutureOrPresent
        val param: MinguoDate
    )

    @RepeatedTest(10)
    fun `should generate minguo date in future or present`(@JavaxFixture fixture: MinguoDateWrapper) {
        Assertions.assertEquals(fixture.param >= JapaneseDate.now(), true)
    }

    data class ThaiBuddhistDateWrapper(
        @field:FutureOrPresent
        val param: ThaiBuddhistDate
    )

    @RepeatedTest(10)
    fun `should generate thai buddhist date in future or present`(@JavaxFixture fixture: ThaiBuddhistDateWrapper) {
        Assertions.assertEquals(fixture.param >= ThaiBuddhistDate.now(), true)
    }
}