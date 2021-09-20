package ru.kontur.kinfra.kfixture.cache.serialization.json

import ru.kontur.kinfra.kfixture.cache.serialization.Serializer

class JsonSerializer : Serializer {
    private val objectMapper = createObjectMapper()

    override suspend fun serialize(source: Any): String {
        return objectMapper.writeValueAsString(source)
    }
}