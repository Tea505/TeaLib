package com.tea505.tealib

class ActionBase(var action: Action): Action {

    private var hasPerform = false

    override suspend fun perform() {
        action.perform()
        hasPerform = true
    }

    fun hasPerformed(): Boolean = hasPerform
}