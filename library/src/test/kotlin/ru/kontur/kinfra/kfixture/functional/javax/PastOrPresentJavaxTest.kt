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
import javax.validation.constraints.PastOrPresent

@ExtendWith(FixtureParameterResolver::class)
class PastOrPresentJavaxTest {
    data class DateWrapper(
        @field:PastOrPresent
        val param: Date
    )

    @RepeatedTest(10)
    fun `should generate date in past or present`(@JavaxFixture fixture: DateWrapper) {
        Assertions.assertEquals(fixture.param <= Date.from(Instant.now()), true)
    }

    data class CalendarWrapper(
        @field:PastOrPresent
        val param: Calendar
    )

    @RepeatedTest(10)
    fun `should generate calendar in past or present`(@JavaxFixture fixture: CalendarWrapper) {
        Assertions.assertEquals(fixture.param <= Calendar.getInstance().also {
            it.time = Date.from(Instant.now())
        }, true)
    }

    data class InstantWrapper(
        @field:PastOrPresent
        val param: Instant
    )

    @RepeatedTest(10)
    fun `should generate instant in past or present`(@JavaxFixture fixture: InstantWrapper) {
        Assertions.assertEquals(fixture.param <= Instant.now(), true)
    }

    data class LocalDateWrapper(
        @field:PastOrPresent
        val param: LocalDate
    )

    @RepeatedTest(10)
    fun `should generate local date in past or present`(@JavaxFixture fixture: LocalDateWrapper) {
        Assertions.assertEquals(fixture.param <= LocalDate.now(), true)
    }

    data class LocalDateTimeWrapper(
        @field:PastOrPresent
        val param: LocalDateTime
    )

    @RepeatedTest(10)
    fun `should generate local date time in past or present`(@JavaxFixture fixture: LocalDateTimeWrapper) {
        Assertions.assertEquals(fixture.param <= LocalDateTime.now(), true)
    }

    data class LocalTimeWrapper(
        @field:PastOrPresent
        val param: LocalTime
    )

    @RepeatedTest(10)
    @Disabled("should properly generate the borders")
    fun `should generate local time in past or present`(@JavaxFixture fixture: LocalTimeWrapper) {
        Assertions.assertEquals(fixture.param <= LocalTime.now(), true)
    }

    data class MonthDayWrapper(
        @field:PastOrPresent
        val param: MonthDay
    )

    @RepeatedTest(10)
    fun `should generate month day in past or present`(@JavaxFixture fixture: MonthDayWrapper) {
        Assertions.assertEquals(fixture.param <= MonthDay.now(), true)
    }

    data class OffsetDateTimeWrapper(
        @field:PastOrPresent
        val param: OffsetDateTime
    )

    @RepeatedTest(10)
    fun `should generate offset date time in past or present`(@JavaxFixture fixture: OffsetDateTimeWrapper) {
        Assertions.assertEquals(fixture.param <= OffsetDateTime.now(), true)
    }

    data class OffsetTimeWrapper(
        @field:PastOrPresent
        val param: OffsetTime
    )

    @RepeatedTest(10)
    @Disabled("should properly generate the borders")
    fun `should generate offset time in past or present`(@JavaxFixture fixture: OffsetTimeWrapper) {
        Assertions.assertEquals(fixture.param <= OffsetTime.now(), true)
    }

    data class YearWrapper(
        @field:PastOrPresent
        val param: Year
    )

    @RepeatedTest(10)
    fun `should generate year in past or present`(@JavaxFixture fixture: YearWrapper) {
        Assertions.assertEquals(fixture.param <= Year.now(), true)
    }

    data class YearMonthWrapper(
        @field:PastOrPresent
        val param: YearMonth
    )

    @RepeatedTest(10)
    fun `should generate year month in past or present`(@JavaxFixture fixture: YearMonthWrapper) {
        Assertions.assertEquals(fixture.param <= YearMonth.now(), true)
    }

    data class ZonedDateTimeWrapper(
        @field:PastOrPresent
        val param: ZonedDateTime
    )

    @RepeatedTest(10)
    fun `should generate zoned date time in past or present`(@JavaxFixture fixture: ZonedDateTimeWrapper) {
        Assertions.assertEquals(fixture.param <= ZonedDateTime.now(), true)
    }

    data class HijrahDateWrapper(
        @field:PastOrPresent
        val param: HijrahDate
    )

    @RepeatedTest(10)
    fun `should generate hijrah date in past or present`(@JavaxFixture fixture: HijrahDateWrapper) {
        Assertions.assertEquals(fixture.param <= HijrahDate.now(), true)
    }

    data class JapaneseDateWrapper(
        @field:PastOrPresent
        val param: JapaneseDate
    )

    @RepeatedTest(10)
    fun `should generate japanese date in past or present`(@JavaxFixture fixture: JapaneseDateWrapper) {
        Assertions.assertEquals(fixture.param <= JapaneseDate.now(), true)
    }

    data class MinguoDateWrapper(
        @field:PastOrPresent
        val param: MinguoDate
    )

    @RepeatedTest(10)
    fun `should generate minguo date in past or present`(@JavaxFixture fixture: MinguoDateWrapper) {
        Assertions.assertEquals(fixture.param <= MinguoDate.now(), true)
    }

    data class ThaiBuddhistDateWrapper(
        @field:PastOrPresent
        val param: ThaiBuddhistDate
    )

    @RepeatedTest(10)
    fun `should generate thai buddhist date in past or present`(@JavaxFixture fixture: ThaiBuddhistDateWrapper) {
        Assertions.assertEquals(fixture.param <= ThaiBuddhistDate.now(), true)
    }
}