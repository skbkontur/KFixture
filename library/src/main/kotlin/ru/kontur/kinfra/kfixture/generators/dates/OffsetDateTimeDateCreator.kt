package ru.kontur.kinfra.kfixture.generators.dates

import java.time.OffsetDateTime

class OffsetDateTimeDateCreator : DateCreator<OffsetDateTime> {
    override fun create(interval: TimeInterval): OffsetDateTime {
        return when (interval) {
            TimeInterval.PAST -> OffsetDateTime.now().minusDays(DAYS_TO_SUB)
            TimeInterval.NOW -> OffsetDateTime.now()
            TimeInterval.FUTURE -> OffsetDateTime.now().plusDays(DAYS_TO_ADD)
        }
    }

    private companion object {
        const val DAYS_TO_SUB = 10L
        const val DAYS_TO_ADD = 10L
    }
}