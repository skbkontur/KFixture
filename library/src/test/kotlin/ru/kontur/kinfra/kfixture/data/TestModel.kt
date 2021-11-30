package ru.kontur.kinfra.kfixture.data

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

data class UserDto(
    val id: UUID,
    val secondIdentifier: UUID,
    val notificationId: UUID,
    val email: String,
    val type: UserType
)

enum class UserType {
    TEST1,
    TEST2,
    TEST3,
    TEST4,
    TEST5,
    TEST6,
    TEST7,
}