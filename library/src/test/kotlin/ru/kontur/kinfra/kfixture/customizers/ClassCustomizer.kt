package ru.kontur.kinfra.kfixture.customizers

import ru.kontur.kinfra.kfixture.api.Customizer
import ru.kontur.kinfra.kfixture.context.FixtureContext

internal class ClassCustomizer : Customizer<ClassForCustomizations> {
    override fun customize(value: ClassForCustomizations, context: FixtureContext): ClassForCustomizations {
        return value.copy(param2 = "AAAA")
    }
}