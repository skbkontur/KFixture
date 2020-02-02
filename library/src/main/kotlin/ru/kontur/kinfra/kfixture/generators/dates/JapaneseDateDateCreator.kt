package ru.kontur.kinfra.kfixture.generators.dates

import java.time.chrono.JapaneseDate
import java.time.temporal.ChronoUnit

class JapaneseDateDateCreator : DateCreator<JapaneseDate> {
    override fun create(interval: TimeInterval): JapaneseDate {
        return when (interval) {
            TimeInterval.PAST -> JapaneseDate.now().minus(DAYS_TO_SUB, ChronoUnit.DAYS)
            TimeInterval.NOW -> JapaneseDate.now()
            TimeInterval.FUTURE -> JapaneseDate.now().plus(DAYS_TO_ADD, ChronoUnit.DAYS)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}