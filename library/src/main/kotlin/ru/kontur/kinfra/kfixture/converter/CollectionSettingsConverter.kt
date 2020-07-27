package ru.kontur.kinfra.kfixture.converter

import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.api.CollectionSettings as AnnotationCollectionSettings

object CollectionSettingsConverter {
    fun convert(source: AnnotationCollectionSettings): CollectionSettings {
        return CollectionSettings(
            size = source.size,
            generateNew = source.generateNew
        )
    }
}