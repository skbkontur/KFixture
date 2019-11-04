package ru.kontur.spring.test.generator.constructors

import ru.kontur.spring.test.generator.api.ValidationConstructor
import java.util.*

class UUIDConstructor : ValidationConstructor<UUID> {
    override fun call(): UUID {
        return UUID.randomUUID()
    }
}