package com.tea505.tealib.actions

class WaitAction(private var milliseconds: Double) {

    /**
    private var timer: Timer = Timer()

    // In Theory this should work
    override suspend fun perform() {

        val start = timer.getMilliSeconds()
        timer.start()

        if (start + milliseconds > timer.getMilliSeconds()) {
            super.hasPerform = false
        }

        super.hasPerform = true

        // delay(milliseconds.roundToLong())
    }
    **/
}