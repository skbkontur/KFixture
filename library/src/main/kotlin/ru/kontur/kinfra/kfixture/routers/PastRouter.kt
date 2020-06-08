package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.PastGenerator
import ru.kontur.kinfra.kfixture.generators.dates.*
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.Past
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class PastRouter<T> : ValidRouter<T, Past> where T : Any {
    private val datePastGenerator = PastGenerator(DateCreatorImpl())
    private val calendarPastGenerator = PastGenerator(CalendarDateCreator())
    private val instantPastGenerator = PastGenerator(InstantDateCreator())
    private val localDatePastGenerator = PastGenerator(LocalDateDateCreator())
    private val localDateTimePastGenerator = PastGenerator(LocalDateTimeDateCreator())
    private val localTimePastGenerator = PastGenerator(LocalTimeDateCreator())
    private val monthDayPastGenerator = PastGenerator(MonthDayDateCreator())
    private val offsetDateTimePastGenerator = PastGenerator(OffsetDateTimeDateCreator())
    private val offsetTimePastGenerator = PastGenerator(OffsetTimeDateCreator())
    private val yearPastGenerator = PastGenerator(YearDateCreator())
    private val yearMonthPastGenerator = PastGenerator(YearMonthDateCreator())
    private val zonedDateTimePastGenerator = PastGenerator(ZonedDateTimeDateCreator())
    private val hijrahDatePastGenerator = PastGenerator(HijrahDateDateCreator())
    private val japaneseDatePastGenerator = PastGenerator(JapaneseDateDateCreator())
    private val minguoDatePastGenerator = PastGenerator(MinguoDateDateCreator())
    private val thaiBuddhistPastGenerator = PastGenerator(ThaiBuddhistDateDateCreator())

    override fun process(param: T, annotation: Past, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Date -> datePastGenerator.process(param, annotation, clazz, type)
            is Calendar -> calendarPastGenerator.process(param, annotation, clazz, type)
            is Instant -> instantPastGenerator.process(param, annotation, clazz, type)
            is LocalDate -> localDatePastGenerator.process(param, annotation, clazz, type)
            is LocalDateTime -> localDateTimePastGenerator.process(param, annotation, clazz, type)
            is LocalTime -> localTimePastGenerator.process(param, annotation, clazz, type)
            is MonthDay -> monthDayPastGenerator.process(param, annotation, clazz, type)
            is OffsetDateTime -> offsetDateTimePastGenerator.process(param, annotation, clazz, type)
            is OffsetTime -> offsetTimePastGenerator.process(param, annotation, clazz, type)
            is Year -> yearPastGenerator.process(param, annotation, clazz, type)
            is YearMonth -> yearMonthPastGenerator.process(param, annotation, clazz, type)
            is ZonedDateTime -> zonedDateTimePastGenerator.process(param, annotation, clazz, type)
            is HijrahDate -> hijrahDatePastGenerator.process(param, annotation, clazz, type)
            is JapaneseDate -> japaneseDatePastGenerator.process(param, annotation, clazz, type)
            is MinguoDate -> minguoDatePastGenerator.process(param, annotation, clazz, type)
            is ThaiBuddhistDate -> thaiBuddhistPastGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}