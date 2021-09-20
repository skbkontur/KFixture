package ru.kontur.kinfra.kfixture.cache.serialization.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun createObjectMapper(): ObjectMapper {
    return ObjectMapper().apply {
        registerModule(KotlinModule())
    }
}