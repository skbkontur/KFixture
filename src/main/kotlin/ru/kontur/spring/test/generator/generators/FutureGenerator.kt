package ru.kontur.spring.test.generator.generators

import ru.kontur.spring.test.generator.api.ValidationParamResolver
import java.time.*
import java.time.chrono.HijrahDate
import java.time.chrono.JapaneseDate
import java.time.chrono.MinguoDate
import java.time.chrono.ThaiBuddhistDate
import java.time.temporal.ChronoUnit
import java.util.*
import javax.validation.constraints.Future
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FutureGenerator : ValidationParamResolver {

    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any {
        val futureAnnotation = annotation as Future

        when (clazz) {
            Date::class -> {
                return Date.from(Instant.now().plus(1000, ChronoUnit.HOURS))
            }
            Calendar::class -> {
                return Calendar.getInstance() //TODO
            }
            Instant::class -> {

            }
            LocalDate::class -> {

            }
            LocalDateTime::class -> {

            }
            LocalTime::class -> {

            }
            MonthDay::class -> {

            }
            OffsetDateTime::class -> {

            }
            OffsetTime::class -> {

            }
            Year::class -> {

            }
            YearMonth::class -> {

            }
            ZonedDateTime::class -> {

            }
            HijrahDate::class -> {

            }
            JapaneseDate::class -> {

            }
            MinguoDate::class -> {

            }
            ThaiBuddhistDate::class -> {

            }
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}