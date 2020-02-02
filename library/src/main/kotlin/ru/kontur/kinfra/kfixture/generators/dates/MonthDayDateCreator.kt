package ru.kontur.kinfra.kfixture.generators.dates

import java.time.MonthDay

class MonthDayDateCreator : DateCreator<MonthDay> {
    override fun create(interval: TimeInterval): MonthDay {
        return when (interval) {
            TimeInterval.PAST -> MonthDay.now().withMonth(0)
            TimeInterval.NOW -> MonthDay.now()
            TimeInterval.FUTURE -> MonthDay.now().withMonth(12)
        }
    }
}