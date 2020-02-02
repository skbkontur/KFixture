package ru.kontur.kinfra.kfixture.generators.dates

import java.time.chrono.ThaiBuddhistDate
import java.time.temporal.ChronoUnit

class ThaiBuddhistDateDateCreator : DateCreator<ThaiBuddhistDate> {
    override fun create(interval: TimeInterval): ThaiBuddhistDate {
        return when (interval) {
            TimeInterval.PAST -> ThaiBuddhistDate.now().minus(DAYS_TO_SUB, ChronoUnit.DAYS)
            TimeInterval.NOW -> ThaiBuddhistDate.now()
            TimeInterval.FUTURE -> ThaiBuddhistDate.now().plus(DAYS_TO_ADD, ChronoUnit.DAYS)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}