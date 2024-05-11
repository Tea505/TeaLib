package com.tea505.tealib.event

class ActionBase(private var action: Action) : Action {

    private var hasPerformed = false

    override fun perform() {
        action.perform()
        hasPerformed = true
    }

}