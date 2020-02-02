package ru.kontur.kinfra.kfixture.generators.dates

import java.time.Instant
import java.util.*

class CalendarDateCreator : DateCreator<Calendar> {
    override fun create(interval: TimeInterval): Calendar {
        return when (interval) {
            TimeInterval.PAST -> Calendar.getInstance().also {
                it.time = Date.from(Instant.now().minusSeconds(DEFAULT_TIME_TO_SUB_S))
            }
            TimeInterval.NOW -> Calendar.getInstance().also {
                it.time = Date.from(Instant.now())
            }
            TimeInterval.FUTURE -> Calendar.getInstance().also {
                it.time = Date.from(Instant.now().plusSeconds(DEFAULT_TIME_TO_ADD_S))
            }
        }
    }

    private companion object {
        const val DEFAULT_TIME_TO_ADD_S = 60000L
        const val DEFAULT_TIME_TO_SUB_S = 60000L
    }
}