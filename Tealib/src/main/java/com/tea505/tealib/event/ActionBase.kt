package com.tea505.tealib.event

/**
 * Base class for actions that can be performed.
 *
 * @property action the action to be performed
 */
class ActionBase(var action: Action) : Action {

    /** Flag indicating whether the action has been performed. */
    var hasPerformed = false

    /**
     * Performs the action and sets the hasPerformed flag to true.
     */
    override fun perform() {
        action.perform()
        hasPerformed = true
    }

}