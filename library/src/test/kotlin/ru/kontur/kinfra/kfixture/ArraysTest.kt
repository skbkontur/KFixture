package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.Fixture
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver

@ExtendWith(FixtureParameterResolver::class)
class ArraysTest {

    data class ByteArrayData(
        val array: ByteArray
    )

    @Test
    fun shouldGenerateDataWithByteArray(@Fixture data: ByteArrayData) {
    }
}