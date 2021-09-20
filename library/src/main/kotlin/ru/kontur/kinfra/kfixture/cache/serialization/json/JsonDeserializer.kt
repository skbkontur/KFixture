package ru.kontur.kinfra.kfixture.cache.serialization.json

import ru.kontur.kinfra.kfixture.cache.serialization.Deserializer

class JsonDeserializer : Deserializer {
    private val objectMapper = createObjectMapper()

    override suspend fun deserialize(source: String, clazz: Class<*>): Any {
        return objectMapper.readValue(source, clazz)
    }
}