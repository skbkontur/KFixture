package ru.kontur.kinfra.kfixture.cache.serialization

interface Deserializer {
    suspend fun deserialize(source: String, clazz: Class<*>): Any
}