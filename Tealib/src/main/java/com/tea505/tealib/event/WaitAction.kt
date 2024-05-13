package com.tea505.tealib.event

class WaitAction(var waitMilliseconds: Double) : Action {

    private val baseAction = ActionBase(object : Action {
        override fun perform() {

        }
    })

    init {
        baseAction.hasPerformed = false
    }

    override fun perform() {
        val startTime: Long = System.nanoTime()
        val waitDurationNanos = (waitMilliseconds * 1e6).toLong()
        while ((startTime + waitDurationNanos) > System.nanoTime()) {
            baseAction.hasPerformed = false
        }

        baseAction.hasPerformed = true

    }


}