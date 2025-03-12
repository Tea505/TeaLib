package com.tea505.tealib.actions

import com.tea505.tealib.Subsystem

interface Action {

    /**
     *  Initializes the action
     */
    fun init() {}

    /**
     * Checks to see if the action has performed
     */
    fun hasPerform(): Boolean = false

    /**
     * The end sequence of the action, can be interrupted or called normally
     */
    fun end(interrupt: Boolean) {}

    /**
     * Performs the activated action
     */
    fun perform() {}

    /**
     * Whether the action runs if the robot is disabled
     */
    fun performWhenDisabled(): Boolean = false

    /**
     * Get the requirements needed for the Subsystem
     */
    fun getRequirements(): Set<Subsystem>

    /**
     * Allows a command to instantly run after the previous one
     */
    fun andThen(vararg actions: Action): Action {
        val consecutiveAction = ConsecutiveAction(this)
        consecutiveAction.addActions(*actions)
        return consecutiveAction
    }
}