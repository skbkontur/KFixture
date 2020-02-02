package ru.kontur.kinfra.kfixture.generators.dates

import java.time.LocalDateTime

class LocalDateTimeDateCreator : DateCreator<LocalDateTime> {
    override fun create(interval: TimeInterval): LocalDateTime {
        return when (interval) {
            TimeInterval.PAST -> LocalDateTime.now().minusDays(DAYS_TO_SUB)
            TimeInterval.NOW -> LocalDateTime.now()
            TimeInterval.FUTURE -> LocalDateTime.now().plusDays(DAYS_TO_ADD)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}