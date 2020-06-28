package ru.kontur.kinfra.kfixture.functional.javax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.annotations.JavaxFixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.Future

@ExtendWith(FixtureParameterResolver::class)
private class FutureJavaxTest {

    data class DateWrapper(
        @field:Future
        val param: Date
    )

    @RepeatedTest(10)
    fun `should generate date in future`(@JavaxFixture fixture: DateWrapper) {
        assertEquals(fixture.param > Date.from(Instant.now()), true)
    }


    data class CalendarWrapper(
        @field:Future
        val param: Calendar
    )

    @RepeatedTest(10)
    fun `should generate calendar in future`(@JavaxFixture fixture: CalendarWrapper) {
        assertEquals(fixture.param > Calendar.getInstance().also {
            it.time = Date.from(Instant.now())
        }, true)
    }

    data class InstantWrapper(
        @field:Future
        val param: Instant
    )

    @RepeatedTest(10)
    fun `should generate instant in future`(@JavaxFixture fixture: InstantWrapper) {
        assertEquals(fixture.param > Instant.now(), true)
    }

    data class LocalDateWrapper(
        @field:Future
        val param: LocalDate
    )

    @RepeatedTest(10)
    fun `should generate local date in future`(@JavaxFixture fixture: LocalDateWrapper) {
        assertEquals(fixture.param > LocalDate.now(), true)
    }

    data class LocalDateTimeWrapper(
        @field:Future
        val param: LocalDateTime
    )

    @RepeatedTest(10)
    fun `should generate local date time in future`(@JavaxFixture fixture: LocalDateTimeWrapper) {
        assertEquals(fixture.param > LocalDateTime.now(), true)
    }

    data class LocalTimeWrapper(
        @field:Future
        val param: LocalTime
    )

    @RepeatedTest(10)
    fun `should generate local time in future`(@JavaxFixture fixture: LocalTimeWrapper) {
        assertEquals(fixture.param > LocalTime.now(), true)
    }

    data class MonthDayWrapper(
        @field:Future
        val param: MonthDay
    )

    @RepeatedTest(10)
    fun `should generate month day in future`(@JavaxFixture fixture: MonthDayWrapper) {
        assertEquals(fixture.param > MonthDay.now(), true)
    }

    data class OffsetDateTimeWrapper(
        @field:Future
        val param: OffsetDateTime
    )

    @RepeatedTest(10)
    fun `should generate offset date time in future`(@JavaxFixture fixture: OffsetDateTimeWrapper) {
        assertEquals(fixture.param > OffsetDateTime.now(), true)
    }

    data class OffsetTimeWrapper(
        @field:Future
        val param: OffsetTime
    )

    @RepeatedTest(10)
    fun `should generate offset time in future`(@JavaxFixture fixture: OffsetTimeWrapper) {
        assertEquals(fixture.param > OffsetTime.now(), true)
    }

    data class YearWrapper(
        @field:Future
        val param: Year
    )

    @RepeatedTest(10)
    fun `should generate year in future`(@JavaxFixture fixture: YearWrapper) {
        assertEquals(fixture.param > Year.now(), true)
    }

    data class YearMonthWrapper(
        @field:Future
        val param: YearMonth
    )

    @RepeatedTest(10)
    fun `should generate year month in future`(@JavaxFixture fixture: YearMonthWrapper) {
        assertEquals(fixture.param > YearMonth.now(), true)
    }

    data class ZonedDateTimeWrapper(
        @field:Future
        val param: ZonedDateTime
    )

    @RepeatedTest(10)
    fun `should generate zoned date time in future`(@JavaxFixture fixture: ZonedDateTimeWrapper) {
        assertEquals(fixture.param > ZonedDateTime.now(), true)
    }

    data class HijrahDateWrapper(
        @field:Future
        val param: HijrahDate
    )

    @RepeatedTest(10)
    fun `should generate hijrah date in future`(@JavaxFixture fixture: HijrahDateWrapper) {
        assertEquals(fixture.param > HijrahDate.now(), true)
    }

    data class JapaneseDateWrapper(
        @field:Future
        val param: JapaneseDate
    )

    @RepeatedTest(10)
    fun `should generate japanese date in future`(@JavaxFixture fixture: JapaneseDateWrapper) {
        assertEquals(fixture.param > JapaneseDate.now(), true)
    }

    data class MinguoDateWrapper(
        @field:Future
        val param: MinguoDate
    )

    @RepeatedTest(10)
    fun `should generate minguo date in future`(@JavaxFixture fixture: MinguoDateWrapper) {
        assertEquals(fixture.param > JapaneseDate.now(), true)
    }

    data class ThaiBuddhistDateWrapper(
        @field:Future
        val param: ThaiBuddhistDate
    )

    @RepeatedTest(10)
    fun `should generate thai buddhist date in future`(@JavaxFixture fixture: ThaiBuddhistDateWrapper) {
        assertEquals(fixture.param > ThaiBuddhistDate.now(), true)
    }
}