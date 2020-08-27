package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.context.FixtureContext
import java.util.*

class CalendarConstructor : ParamConstructor<Calendar> {
    override fun call(context: FixtureContext): Calendar {
        return Calendar.getInstance()
    }
}