package ru.kontur.kinfra.kfixture.generators.dates

import java.time.ZonedDateTime

class ZonedDateTimeDateCreator : DateCreator<ZonedDateTime> {
    override fun create(interval: TimeInterval): ZonedDateTime {
        return when (interval) {
            TimeInterval.PAST -> ZonedDateTime.now().minusDays(DAYS_TO_SUB)
            TimeInterval.NOW -> ZonedDateTime.now()
            TimeInterval.FUTURE -> ZonedDateTime.now().plusDays(DAYS_TO_ADD)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}