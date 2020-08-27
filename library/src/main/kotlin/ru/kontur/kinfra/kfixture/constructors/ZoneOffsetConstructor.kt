package ru.kontur.kinfra.kfixture.constructors

import ru.kontur.kinfra.kfixture.api.ParamConstructor
import ru.kontur.kinfra.kfixture.context.FixtureContext
import java.time.ZoneOffset
import kotlin.random.Random

class ZoneOffsetConstructor : ParamConstructor<ZoneOffset> {
    override fun call(context: FixtureContext): ZoneOffset {
        return ZoneOffset.ofTotalSeconds(Random.nextInt(60))
    }
}