package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.AssertFalseGenerator
import javax.validation.constraints.AssertFalse

class AssertFalseRouter : ValidRouter<Boolean, AssertFalse> {
    private val assertFalseGenerator = AssertFalseGenerator()

    override fun process(param: Boolean, annotation: AssertFalse): Boolean {
        return assertFalseGenerator.process(param, annotation)
    }
}