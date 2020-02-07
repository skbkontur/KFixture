package ru.kontur.kinfra.kfixture.generators

interface DigitsCreator<T> {
    fun create(integer: Int, fraction: Int): T
}