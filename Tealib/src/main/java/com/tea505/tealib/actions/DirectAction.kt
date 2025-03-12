package com.tea505.tealib.actions

import com.tea505.tealib.Subsystem

class DirectAction(
    private val run: Runnable = Runnable { },
    vararg  requirements: Subsystem
): ActionBase() {

    init {
        addRequirements(*requirements)
    }

    override fun init() {
        run.run()
    }

    override fun hasPerform(): Boolean {
        return true
    }
}