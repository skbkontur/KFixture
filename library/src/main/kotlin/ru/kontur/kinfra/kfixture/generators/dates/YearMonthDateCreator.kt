package ru.kontur.kinfra.kfixture.generators.dates

import java.time.YearMonth

class YearMonthDateCreator : DateCreator<YearMonth> {
    override fun create(interval: TimeInterval): YearMonth {
        return when (interval) {
            TimeInterval.PAST -> YearMonth.now().minusYears(YEARS_TO_SUB)
            TimeInterval.NOW -> YearMonth.now()
            TimeInterval.FUTURE -> YearMonth.now().plusYears(YEARS_TO_ADD)
        }
    }

    private companion object {
        const val YEARS_TO_SUB = 10L
        const val YEARS_TO_ADD = 10L
    }
}