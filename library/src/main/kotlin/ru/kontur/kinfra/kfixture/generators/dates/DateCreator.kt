package ru.kontur.kinfra.kfixture.generators.dates

interface DateCreator<T> {
    fun create(interval: TimeInterval): T
}