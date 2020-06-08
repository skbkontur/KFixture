package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.exceptions.AnnotationCantBeAppliedException
import ru.kontur.kinfra.kfixture.generators.FutureOrPresentGenerator
import ru.kontur.kinfra.kfixture.generators.dates.*
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.util.*
import javax.validation.constraints.FutureOrPresent
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("unused")
class FutureOrPresentRouter<T> : ValidRouter<T, FutureOrPresent> where T : Any, T : Comparable<T> {
    private val dateFutureOrPresentGenerator = FutureOrPresentGenerator(DateCreatorImpl())
    private val calendarFutureOrPresentGenerator = FutureOrPresentGenerator(CalendarDateCreator())
    private val instantFutureOrPresentGenerator = FutureOrPresentGenerator(InstantDateCreator())
    private val localDateFutureOrPresentGenerator = FutureOrPresentGenerator(LocalDateDateCreator())
    private val localDateTimeFutureOrPresentGenerator = FutureOrPresentGenerator(LocalDateTimeDateCreator())
    private val localTimeFutureOrPresentGenerator = FutureOrPresentGenerator(LocalTimeDateCreator())
    private val monthDayFutureOrPresentGenerator = FutureOrPresentGenerator(MonthDayDateCreator())
    private val offsetDateTimeFutureOrPresentGenerator = FutureOrPresentGenerator(OffsetDateTimeDateCreator())
    private val offsetTimeFutureOrPresentGenerator = FutureOrPresentGenerator(OffsetTimeDateCreator())
    private val yearFutureOrPresentGenerator = FutureOrPresentGenerator(YearDateCreator())
    private val yearMonthFutureOrPresentGenerator = FutureOrPresentGenerator(YearMonthDateCreator())
    private val zonedDateTimeFutureOrPresentGenerator = FutureOrPresentGenerator(ZonedDateTimeDateCreator())
    private val hijrahDateFutureOrPresentGenerator = FutureOrPresentGenerator(HijrahDateDateCreator())
    private val japaneseDateFutureOrPresentGenerator = FutureOrPresentGenerator(JapaneseDateDateCreator())
    private val minguoDateFutureOrPresentGenerator = FutureOrPresentGenerator(MinguoDateDateCreator())
    private val thaiBuddhistFutureOrPresentGenerator = FutureOrPresentGenerator(ThaiBuddhistDateDateCreator())

    override fun process(param: T, annotation: FutureOrPresent, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is Date -> dateFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is Calendar -> calendarFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is Instant -> instantFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is LocalDate -> localDateFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is LocalDateTime -> localDateTimeFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is LocalTime -> localTimeFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is MonthDay -> monthDayFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is OffsetDateTime -> offsetDateTimeFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is OffsetTime -> offsetTimeFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is Year -> yearFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is YearMonth -> yearMonthFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is ZonedDateTime -> zonedDateTimeFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is HijrahDate -> hijrahDateFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is JapaneseDate -> japaneseDateFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is MinguoDate -> minguoDateFutureOrPresentGenerator.process(param, annotation, clazz, type)
            is ThaiBuddhistDate -> thaiBuddhistFutureOrPresentGenerator.process(param, annotation, clazz, type)
            else -> {
                throw AnnotationCantBeAppliedException(annotation, clazz)
            }
        }
    }
}