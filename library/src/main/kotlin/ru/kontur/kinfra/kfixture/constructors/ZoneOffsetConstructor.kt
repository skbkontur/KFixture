package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ValidationConstructor
import java.time.ZoneOffset
import kotlin.random.Random

class ZoneOffsetConstructor : ValidationConstructor<ZoneOffset> {
    override fun call(): ZoneOffset {
        return ZoneOffset.ofTotalSeconds(Random.nextInt(60))
    }
}