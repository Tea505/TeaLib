package com.tea505.tealib.event

class ActionSequence {

    var hasPerformed: Boolean = false
    private var actionList = ArrayList<ActionBase>()
    private var actionRunner: Runnable? = null
    private var actionThreading: Thread? = null

    init {
        hasPerformed = true
    }
    fun addAction(action: Action): ActionSequence {
        val actionBase = ActionBase(action)
        actionList.add(actionBase)
        return this
    }

    fun waitAction(milliseconds: Double): ActionSequence {
        val waitAction = WaitAction(milliseconds)
        val actionBase = ActionBase(waitAction)
        actionList.add(actionBase)
        return this
    }

    fun build(): ActionSequence {
        actionRunner = Runnable {
            for (action in actionList) {
                action.perform()
            }
            hasPerformed = true
        }
        return this
    }

    fun perform() {
        hasPerformed = false
        actionThreading = Thread(actionRunner)
        actionThreading?.start()
    }

    fun trigger() {
        if (hasPerformed) {
            perform()
        }
    }

}