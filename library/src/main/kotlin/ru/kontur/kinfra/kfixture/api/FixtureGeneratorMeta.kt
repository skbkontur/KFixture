package ru.kontur.kinfra.kfixture.api

/**
 * This class used to provide extra path and settings for generation
 */
annotation class FixtureGeneratorMeta(
    val scanner: ScannerSettings = ScannerSettings(paths = arrayOf()),
    val generation: GenerationSettings = GenerationSettings(),
    val collection: CollectionSettings = CollectionSettings(),
    val cacheSettings: CacheSettings = CacheSettings(type = CacheType.WITHOUT_CACHE)
)

annotation class CacheSettings(
    val type: CacheType
)

annotation class ScannerSettings(
    val paths: Array<String>
)

annotation class CollectionSettings(
    val size: Int = 10,
    val generateNew: Boolean = true
)

annotation class GenerationSettings(
    val generateNew: Boolean = true
)