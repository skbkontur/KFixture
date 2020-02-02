package ru.kontur.kinfra.kfixture.generators.dates

import java.time.chrono.MinguoDate
import java.time.temporal.ChronoUnit

class MinguoDateDateCreator : DateCreator<MinguoDate> {
    override fun create(interval: TimeInterval): MinguoDate {
        return when (interval) {
            TimeInterval.PAST -> MinguoDate.now().minus(DAYS_TO_SUB, ChronoUnit.DAYS)
            TimeInterval.NOW -> MinguoDate.now()
            TimeInterval.FUTURE -> MinguoDate.now().plus(DAYS_TO_ADD, ChronoUnit.DAYS)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}