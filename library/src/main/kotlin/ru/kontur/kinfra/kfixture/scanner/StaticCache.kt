package ru.kontur.kinfra.kfixture.scanner

import ru.kontur.kinfra.kfixture.processor.scanner.CachedAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScannerImpl

object StaticCache {
    private var currentPathes: List<String> = listOf()
    private lateinit var cachedScanner: CachedAnnotationScanner

    fun getScanner(paths: List<String>): CachedAnnotationScanner {
        if (::cachedScanner.isInitialized.not() || currentPathes != paths) {
            currentPathes = paths
            cachedScanner = CachedAnnotationScanner(GeneratorAnnotationScannerImpl(paths.toList()))
        }
        return cachedScanner
    }
}