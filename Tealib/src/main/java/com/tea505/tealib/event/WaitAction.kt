package com.tea505.tealib.event

/**
 * Class representing a wait action.
 *
 * @property waitMilliseconds the duration to wait in milliseconds
 */
class WaitAction(var waitMilliseconds: Double) : Action {

    /** The base action for waiting. */
    private val baseAction = ActionBase(object : Action {
        override fun perform() {

        }
    })

    /**
     * Initializes the wait action.
     */
    init {
        baseAction.hasPerformed = false
    }

    /**
     * Performs the wait action.
     */
    override fun perform() {
        val startTime: Long = System.nanoTime()
        val waitDurationNanos = (waitMilliseconds * 1e6).toLong()
        while ((startTime + waitDurationNanos) > System.nanoTime()) {
            baseAction.hasPerformed = false
        }
        baseAction.hasPerformed = true
    }
}