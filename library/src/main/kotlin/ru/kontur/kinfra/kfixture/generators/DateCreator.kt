package ru.kontur.kinfra.kfixture.generators

interface DateCreator<T> {
    fun create(interval: TimeInterval): T
}

enum class TimeInterval {
    PAST,
    NOW,
    FUTURE
}