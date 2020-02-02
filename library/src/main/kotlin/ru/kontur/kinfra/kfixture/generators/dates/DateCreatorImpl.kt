package ru.kontur.kinfra.kfixture.generators.dates

import java.time.Instant
import java.util.*

class DateCreatorImpl : DateCreator<Date> {
    override fun create(interval: TimeInterval): Date {
        return when (interval) {
            TimeInterval.PAST -> Date.from(Instant.now().minusSeconds(DEFAULT_TIME_TO_SUB_S))
            TimeInterval.NOW -> Date.from(Instant.now())
            TimeInterval.FUTURE -> Date.from(Instant.now().plusSeconds(DEFAULT_TIME_TO_ADD_S))
        }
    }

    private companion object {
        const val DEFAULT_TIME_TO_ADD_S = 60000L
        const val DEFAULT_TIME_TO_SUB_S = 60000L
    }
}