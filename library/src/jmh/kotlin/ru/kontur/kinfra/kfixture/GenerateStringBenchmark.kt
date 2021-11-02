package ru.kontur.kinfra.kfixture

import org.openjdk.jmh.annotations.*
import ru.kontur.kinfra.kfixture.misc.generateString

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 0)
@Measurement(iterations = 2, batchSize = 1)
@State(value = Scope.Benchmark)
open class GenerateStringBenchmark {

    @Benchmark
    fun generateTenString() {
        generateString(10)
    }
}