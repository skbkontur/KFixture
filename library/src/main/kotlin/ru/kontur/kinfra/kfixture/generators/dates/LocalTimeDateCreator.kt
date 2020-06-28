package ru.kontur.kinfra.kfixture.generators.dates

import java.time.LocalTime

class LocalTimeDateCreator : DateCreator<LocalTime> {
    override fun create(interval: TimeInterval): LocalTime {
        return when (interval) {
            TimeInterval.PAST -> LocalTime.now().minusHours(HOURS_TO_SUB)
            TimeInterval.NOW -> LocalTime.now()
            TimeInterval.FUTURE -> LocalTime.now().plusHours(HOURS_TO_ADD)
        }
    }

    private companion object {
        const val HOURS_TO_SUB = 1L
        const val HOURS_TO_ADD = 1L
    }
}