package ru.kontur.kinfra.kfixture.cache.serialization

interface Serializer {
    suspend fun serialize(source: Any): String
}