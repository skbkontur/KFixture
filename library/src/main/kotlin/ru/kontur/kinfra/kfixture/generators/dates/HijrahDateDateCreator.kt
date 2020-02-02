package ru.kontur.kinfra.kfixture.generators.dates

import java.time.chrono.HijrahDate
import java.time.temporal.ChronoUnit

class HijrahDateDateCreator : DateCreator<HijrahDate> {
    override fun create(interval: TimeInterval): HijrahDate {
        return when (interval) {
            TimeInterval.PAST -> HijrahDate.now().minus(DAYS_TO_SUB, ChronoUnit.DAYS)
            TimeInterval.NOW -> HijrahDate.now()
            TimeInterval.FUTURE -> HijrahDate.now().plus(DAYS_TO_ADD, ChronoUnit.DAYS)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}