package ru.kontur.kinfra.kfixture.generators.dates

import java.time.Instant

class InstantDateCreator : DateCreator<Instant> {
    override fun create(interval: TimeInterval): Instant {
        return when (interval) {
            TimeInterval.PAST -> Instant.now().minusSeconds(DEFAULT_TIME_TO_SUB_S)
            TimeInterval.NOW -> Instant.now()
            TimeInterval.FUTURE -> Instant.now().plusSeconds(DEFAULT_TIME_TO_ADD_S)
        }
    }

    private companion object {
        const val DEFAULT_TIME_TO_ADD_S = 60000L
        const val DEFAULT_TIME_TO_SUB_S = 60000L
    }
}