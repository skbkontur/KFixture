package ru.kontur.spring.test.generator.constructors

import ru.kontur.spring.test.generator.api.ValidationConstructor
import java.time.ZoneOffset
import kotlin.random.Random

class ZoneOffsetConstructor : ValidationConstructor<ZoneOffset> {
    override fun call(): ZoneOffset {
        return ZoneOffset.ofTotalSeconds(Random.nextInt(60))
    }
}