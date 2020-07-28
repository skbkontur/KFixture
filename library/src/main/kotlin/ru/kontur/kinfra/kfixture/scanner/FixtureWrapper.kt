package ru.kontur.kinfra.kfixture.scanner

import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.CachedAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScannerImpl

class FixtureWrapper(
    collectionSettings: CollectionSettings,
    val paths: List<String>
) {
    val fixtureProcessor: FixtureProcessor = FixtureProcessor(
        collectionSettings,
        CachedAnnotationScanner(GeneratorAnnotationScannerImpl(Reflections(paths)))
    )
}