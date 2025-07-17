package com.tea505.tealib.utils

class Timer {

    private var startTime: Long = System.nanoTime()
    private var elapsedTimeBeforeReset: Long = 0

    @Synchronized
    fun reset() {
        elapsedTimeBeforeReset += System.nanoTime() - startTime
        startTime = System.nanoTime()
    }

    @Synchronized
    fun start() {
        elapsedTimeBeforeReset = 0
        startTime = System.nanoTime()
    }

    fun getSeconds(): Double {
        val totalElapsedTime = elapsedTimeBeforeReset + (System.nanoTime() - startTime)
        return totalElapsedTime * 1e-9
    }

    fun getMilliSeconds(): Double {
        val totalElapsedTime = elapsedTimeBeforeReset + (System.nanoTime() - startTime)
        return totalElapsedTime * 1e-6
    }
}