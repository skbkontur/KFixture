package ru.kontur.kinfra.kfixture.generators.dates

import java.time.OffsetTime

class OffsetTimeDateCreator : DateCreator<OffsetTime> {
    override fun create(interval: TimeInterval): OffsetTime {
        return when (interval) {
            TimeInterval.PAST -> OffsetTime.now().minusHours(HOURS_TO_SUB)
            TimeInterval.NOW -> OffsetTime.now()
            TimeInterval.FUTURE -> OffsetTime.now().plusHours(HOURS_TO_ADD)
        }
    }

    private companion object {
        const val HOURS_TO_SUB = 1L
        const val HOURS_TO_ADD = 1L
    }
}