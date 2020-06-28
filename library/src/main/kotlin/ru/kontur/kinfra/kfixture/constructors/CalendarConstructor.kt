package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import java.util.*

class CalendarConstructor : ParamConstructor<Calendar> {
    override fun call(): Calendar {
        return Calendar.getInstance()
    }
}