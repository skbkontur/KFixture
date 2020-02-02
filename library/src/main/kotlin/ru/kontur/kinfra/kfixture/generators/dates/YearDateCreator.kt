package ru.kontur.kinfra.kfixture.generators.dates

import java.time.Year

class YearDateCreator : DateCreator<Year> {
    override fun create(interval: TimeInterval): Year {
        return when (interval) {
            TimeInterval.PAST -> Year.now().minusYears(YEARS_TO_SUB)
            TimeInterval.NOW -> Year.now()
            TimeInterval.FUTURE -> Year.now().plusYears(YEARS_TO_ADD)
        }
    }

    private companion object {
        const val YEARS_TO_SUB = 10L
        const val YEARS_TO_ADD = 10L
    }
}