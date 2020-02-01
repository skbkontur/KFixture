package ru.kontur.kinfra.kfixture.routers

import ru.kontur.kinfra.kfixture.generators.AssertTrueGenerator
import javax.validation.constraints.AssertTrue

class AssertTrueRouter : ValidRouter<Boolean, AssertTrue> {
    private val assertTrueGenerator = AssertTrueGenerator()

    override fun process(param: Boolean, annotation: AssertTrue): Boolean {
        return assertTrueGenerator.process(param, annotation)
    }
}