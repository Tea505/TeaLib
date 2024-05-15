package com.tea505.tealib.event

/**
 * Class representing a sequence of actions to be performed.
 */
class ActionSequence() {

    /** Flag indicating whether the sequence has been performed. */
    var hasPerformed: Boolean = false

    /** List of actions in the sequence. */
    private var actionList = ArrayList<ActionBase>()

    /** Runnable to execute the actions. */
    private var actionRunner: Runnable? = null

    /** Thread for executing the actions. */
    private var actionThreading: Thread? = null

    /**
     * Initializes the action sequence.
     */
    init {
        hasPerformed = true
    }

    /**
     * Adds an action to the sequence.
     *
     * @param action the action to add
     * @return the action sequence
     */
    fun addAction(action: Action): ActionSequence {
        val actionBase = ActionBase(action)
        actionList.add(actionBase)
        return this
    }

    /**
     * Adds a wait action to the sequence.
     *
     * @param milliseconds the duration to wait in milliseconds
     * @return the action sequence
     */
    fun addWaitAction(milliseconds: Double): ActionSequence {
        val waitAction = WaitAction(milliseconds)
        val actionBase = ActionBase(waitAction)
        actionList.add(actionBase)
        return this
    }

    /**
     * Builds the action sequence.
     *
     * @return the action sequence
     */
    fun build(): ActionSequence {
        actionRunner = Runnable {
            for (action in actionList) {
                action.perform()
            }
            hasPerformed = true
        }
        return this
    }

    /**
     * Performs the action sequence.
     */
    fun perform() {
        hasPerformed = false
        actionThreading = Thread(actionRunner)
        actionThreading?.start()
    }

    /**
     * Triggers the action sequence if it has not been performed.
     */
    fun trigger() {
        if (hasPerformed) {
            perform()
        }
    }
}