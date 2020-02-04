package ru.kontur.kinfra.kfixture.converter

import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.processor.GenerationProcessorMeta

object FixtureMetaConverter {
    fun convert(source: FixtureGeneratorMeta): GenerationProcessorMeta {
        return GenerationProcessorMeta(
            defaultSize = source.defaultSize,
            collectionLength = source.collectionLength,
            mapSize = source.mapSize
        )
    }
}