package ru.kontur.kinfra.kfixture.generators.dates

import java.time.LocalDate

class LocalDateDateCreator : DateCreator<LocalDate> {
    override fun create(interval: TimeInterval): LocalDate {
        return when (interval) {
            TimeInterval.PAST -> LocalDate.now().minusDays(DAYS_TO_SUB)
            TimeInterval.NOW -> LocalDate.now()
            TimeInterval.FUTURE -> LocalDate.now().plusDays(DAYS_TO_ADD)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}