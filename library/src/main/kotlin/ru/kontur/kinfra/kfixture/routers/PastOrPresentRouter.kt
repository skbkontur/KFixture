package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.PastOrPresentGenerator
import ru.kontur.kinfra.kfixture.generators.dates.*
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.PastOrPresent
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class PastOrPresentRouter<T> : ValidRouter<T, PastOrPresent> where T : Any {
    private val datePastOrPresentGenerator = PastOrPresentGenerator(DateCreatorImpl())
    private val calendarPastOrPresentGenerator = PastOrPresentGenerator(CalendarDateCreator())
    private val instantPastOrPresentGenerator = PastOrPresentGenerator(InstantDateCreator())
    private val localDatePastOrPresentGenerator = PastOrPresentGenerator(LocalDateDateCreator())
    private val localDateTimePastOrPresentGenerator = PastOrPresentGenerator(LocalDateTimeDateCreator())
    private val localTimePastOrPresentGenerator = PastOrPresentGenerator(LocalTimeDateCreator())
    private val monthDayPastOrPresentGenerator = PastOrPresentGenerator(MonthDayDateCreator())
    private val offsetDateTimePastOrPresentGenerator = PastOrPresentGenerator(OffsetDateTimeDateCreator())
    private val offsetTimePastOrPresentGenerator = PastOrPresentGenerator(OffsetTimeDateCreator())
    private val yearPastOrPresentGenerator = PastOrPresentGenerator(YearDateCreator())
    private val yearMonthPastOrPresentGenerator = PastOrPresentGenerator(YearMonthDateCreator())
    private val zonedDateTimePastOrPresentGenerator = PastOrPresentGenerator(ZonedDateTimeDateCreator())
    private val hijrahDatePastOrPresentGenerator = PastOrPresentGenerator(HijrahDateDateCreator())
    private val japaneseDatePastOrPresentGenerator = PastOrPresentGenerator(JapaneseDateDateCreator())
    private val minguoDatePastOrPresentGenerator = PastOrPresentGenerator(MinguoDateDateCreator())
    private val thaiBuddhistPastOrPresentGenerator = PastOrPresentGenerator(ThaiBuddhistDateDateCreator())
    
    override fun process(param: T, annotation: PastOrPresent, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Date -> datePastOrPresentGenerator.process(param, annotation, clazz, type)
            is Calendar -> calendarPastOrPresentGenerator.process(param, annotation, clazz, type)
            is Instant -> instantPastOrPresentGenerator.process(param, annotation, clazz, type)
            is LocalDate -> localDatePastOrPresentGenerator.process(param, annotation, clazz, type)
            is LocalDateTime -> localDateTimePastOrPresentGenerator.process(param, annotation, clazz, type)
            is LocalTime -> localTimePastOrPresentGenerator.process(param, annotation, clazz, type)
            is MonthDay -> monthDayPastOrPresentGenerator.process(param, annotation, clazz, type)
            is OffsetDateTime -> offsetDateTimePastOrPresentGenerator.process(param, annotation, clazz, type)
            is OffsetTime -> offsetTimePastOrPresentGenerator.process(param, annotation, clazz, type)
            is Year -> yearPastOrPresentGenerator.process(param, annotation, clazz, type)
            is YearMonth -> yearMonthPastOrPresentGenerator.process(param, annotation, clazz, type)
            is ZonedDateTime -> zonedDateTimePastOrPresentGenerator.process(param, annotation, clazz, type)
            is HijrahDate -> hijrahDatePastOrPresentGenerator.process(param, annotation, clazz, type)
            is JapaneseDate -> japaneseDatePastOrPresentGenerator.process(param, annotation, clazz, type)
            is MinguoDate -> minguoDatePastOrPresentGenerator.process(param, annotation, clazz, type)
            is ThaiBuddhistDate -> thaiBuddhistPastOrPresentGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}