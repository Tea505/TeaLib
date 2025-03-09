package com.tea505.tealib.actions

class ActionState(private var interruptible: Boolean) {

    fun isInterruptible(): Boolean = interruptible

}