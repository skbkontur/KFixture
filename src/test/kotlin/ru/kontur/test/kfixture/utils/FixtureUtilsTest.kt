package ru.kontur.test.kfixture.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FixtureUtilsTest {

    @Test
    fun testGenerateSimpleClazz() {
        data class TestData(
            val param: String
        )

        val data = FixtureUtils.createClazz<TestData>("ru.kontur.spring.test.generate")
        assertNotNull(data)
    }

    @Test
    fun `should generate list`() {
        data class TestData(
            val list: List<String>
        )

        TestData(
            list = FixtureUtils.createList("ru.kontur.spring.test.generate")
        )
    }

    @Test
    fun `should generate map`() {
        data class TestData(
            val map: Map<String, String>
        )

        TestData(
            map = FixtureUtils.createMap("ru.kontur.spring.test.generate")
        )
    }
}