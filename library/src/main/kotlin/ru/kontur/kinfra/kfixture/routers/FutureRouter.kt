package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.FutureGenerator
import ru.kontur.kinfra.kfixture.generators.dates.*
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.Future
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class FutureRouter<T> : ValidRouter<T, Future> where T : Any, T : Comparable<T> {
    private val dateFutureGenerator = FutureGenerator(DateCreatorImpl())
    private val calendarFutureGenerator = FutureGenerator(CalendarDateCreator())
    private val instantFutureGenerator = FutureGenerator(InstantDateCreator())
    private val localDateFutureGenerator = FutureGenerator(LocalDateDateCreator())
    private val localDateTimeFutureGenerator = FutureGenerator(LocalDateTimeDateCreator())
    private val localTimeFutureGenerator = FutureGenerator(LocalTimeDateCreator())
    private val monthDayFutureGenerator = FutureGenerator(MonthDayDateCreator())
    private val offsetDateTimeFutureGenerator = FutureGenerator(OffsetDateTimeDateCreator())
    private val offsetTimeFutureGenerator = FutureGenerator(OffsetTimeDateCreator())
    private val yearFutureGenerator = FutureGenerator(YearDateCreator())
    private val yearMonthFutureGenerator = FutureGenerator(YearMonthDateCreator())
    private val zonedDateTimeFutureGenerator = FutureGenerator(ZonedDateTimeDateCreator())
    private val hijrahDateFutureGenerator = FutureGenerator(HijrahDateDateCreator())
    private val japaneseDateFutureGenerator = FutureGenerator(JapaneseDateDateCreator())
    private val minguoDateFutureGenerator = FutureGenerator(MinguoDateDateCreator())
    private val thaiBuddhistFutureGenerator = FutureGenerator(ThaiBuddhistDateDateCreator())

    override fun process(param: T, annotation: Future, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Date -> dateFutureGenerator.process(param, annotation, clazz, type)
            is Calendar -> calendarFutureGenerator.process(param, annotation, clazz, type)
            is Instant -> instantFutureGenerator.process(param, annotation, clazz, type)
            is LocalDate -> localDateFutureGenerator.process(param, annotation, clazz, type)
            is LocalDateTime -> localDateTimeFutureGenerator.process(param, annotation, clazz, type)
            is LocalTime -> localTimeFutureGenerator.process(param, annotation, clazz, type)
            is MonthDay -> monthDayFutureGenerator.process(param, annotation, clazz, type)
            is OffsetDateTime -> offsetDateTimeFutureGenerator.process(param, annotation, clazz, type)
            is OffsetTime -> offsetTimeFutureGenerator.process(param, annotation, clazz, type)
            is Year -> yearFutureGenerator.process(param, annotation, clazz, type)
            is YearMonth -> yearMonthFutureGenerator.process(param, annotation, clazz, type)
            is ZonedDateTime -> zonedDateTimeFutureGenerator.process(param, annotation, clazz, type)
            is HijrahDate -> hijrahDateFutureGenerator.process(param, annotation, clazz, type)
            is JapaneseDate -> japaneseDateFutureGenerator.process(param, annotation, clazz, type)
            is MinguoDate -> minguoDateFutureGenerator.process(param, annotation, clazz, type)
            is ThaiBuddhistDate -> thaiBuddhistFutureGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}