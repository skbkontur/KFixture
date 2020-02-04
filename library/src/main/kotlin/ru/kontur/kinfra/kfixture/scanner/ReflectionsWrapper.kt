package ru.kontur.kinfra.kfixture.scanner

import org.reflections.Reflections

class ReflectionsWrapper(
    val pathes: List<String>
) {
    val reflections: Reflections = Reflections(pathes)
}