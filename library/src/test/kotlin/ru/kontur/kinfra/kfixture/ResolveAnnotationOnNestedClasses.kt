package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.annotations.Fixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import java.util.*

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.kinfra"])
class ResolveAnnotationOnNestedClasses {

    // TODO переделать на юниты
    @Nested
    inner class NestedClass {
        @Nested
        inner class TwoLevelInner {
            @Test
            fun `should generate`(@Fixture uuid: UUID) {
            }
        }
    }

}