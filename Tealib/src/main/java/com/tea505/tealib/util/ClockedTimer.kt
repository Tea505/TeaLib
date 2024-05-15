package com.tea505.tealib.util

class ClockedTimer {

    var prevTime: Double

    init {
        this.prevTime = getSeconds()
    }

    fun currentTime(): Double {
        return getSeconds() - prevTime
    }

    fun reset() {
        this.prevTime = currentTime()
    }

    fun getSeconds(): Double {
        return System.currentTimeMillis() / 1000.0
    }
}