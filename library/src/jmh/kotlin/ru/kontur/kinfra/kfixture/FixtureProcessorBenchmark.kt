package ru.kontur.kinfra.kfixture

import org.openjdk.jmh.annotations.*
import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.data.objects.random1.RandomDataStructure
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.CachedAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScannerImpl
import ru.kontur.kinfra.kfixture.utils.toKType

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 0)
@Measurement(iterations = 5, batchSize = 1)
@State(value = Scope.Benchmark)
open class FixtureProcessorBenchmark {
    private lateinit var processor: FixtureProcessor
    private val clazz = RandomDataStructure::class.java

    @Setup
    fun setup() {
        processor = FixtureProcessor(
            collectionSettings = CollectionSettings(),
            generatorAnnotationScanner = CachedAnnotationScanner(
                GeneratorAnnotationScannerImpl(Reflections())
            )
        )
    }

    @Benchmark
    fun sum() {
        for (i in 0 until 10)
            processor.generateParam(clazz.kotlin, clazz.toKType(), null)
    }
}