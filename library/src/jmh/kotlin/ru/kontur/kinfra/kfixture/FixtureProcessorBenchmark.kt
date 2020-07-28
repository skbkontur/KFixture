package ru.kontur.kinfra.kfixture

import org.openjdk.jmh.annotations.*
import org.reflections.Reflections
import ru.kontur.kinfra.kfixture.data.objects.random1.RandomDataStructure
import ru.kontur.kinfra.kfixture.model.CollectionSettings
import ru.kontur.kinfra.kfixture.processor.impl.FixtureProcessor
import ru.kontur.kinfra.kfixture.processor.scanner.CachedAnnotationScanner
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScannerImpl
import ru.kontur.kinfra.kfixture.utils.toKType

@BenchmarkMode(Mode.SampleTime)
@Warmup(iterations = 1)
@Measurement(iterations = 2, batchSize = 5)
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
        processor.generateParam(clazz.kotlin, clazz.toKType(), null)
    }
}