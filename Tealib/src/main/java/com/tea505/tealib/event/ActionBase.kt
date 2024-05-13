package com.tea505.tealib.event

class ActionBase(var action: Action) : Action {

    var hasPerformed = false

    override fun perform() {
        action.perform()
        hasPerformed = true
    }

}