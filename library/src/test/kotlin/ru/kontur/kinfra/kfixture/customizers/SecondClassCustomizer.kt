package ru.kontur.kinfra.kfixture.customizers

import ru.kontur.kinfra.kfixture.api.Customizer
import ru.kontur.kinfra.kfixture.context.FixtureContext

internal class SecondClassCustomizer : Customizer<ClassForCustomizations> {
    override fun customize(value: ClassForCustomizations, context: FixtureContext): ClassForCustomizations {
        return value.copy(param1 = "<<<<")
    }
}