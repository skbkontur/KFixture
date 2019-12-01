package ru.kontur.test.kfixture.data

import java.util.*

data class TestModel(
    val id: String,
    val secondId: String,
    val param: TestEnum,
    val thirdId: UUID,
    val someParam: List<EmbeddedClass>,
    val anotherParam: List<String>,
    val version: Long? = null
)

data class EmbeddedClass(
    val id: String,
    val status: TestEnumWithStatus
)

enum class TestEnum {
    EN
}

enum class TestEnumWithStatus {
    BLA
}