package com.tea505.tealib

import kotlinx.coroutines.delay
import kotlin.math.roundToLong

class WaitAction(private var milliseconds: Double): Action {

    override suspend fun perform() {
        delay(milliseconds.roundToLong())
    }
}