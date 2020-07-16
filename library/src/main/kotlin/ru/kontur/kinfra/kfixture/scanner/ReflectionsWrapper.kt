package ru.kontur.kinfra.kfixture.scanner

import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.processor.scanner.CachedAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScannerImpl

class ReflectionsWrapper(
    val pathes: List<String>
) {
    val cachedScanner: CachedAnnotationScanner =
        CachedAnnotationScanner(GeneratorAnnotationScannerImpl(Reflections(pathes)))
}