package com.tea505.tealib.actions

import com.tea505.tealib.Subsystem

abstract class ActionBase: Action {

    protected var mName: String = this::class.simpleName ?: "Unknown"
    protected var mSubsystem: String = "NullGroup"
    protected var mRequirements: MutableSet<Subsystem> = HashSet()

    fun addRequirements(vararg requirements: Subsystem) {
        mRequirements.addAll(requirements)
    }

    override fun getRequirements(): MutableSet<Subsystem> {
        return mRequirements
    }

    fun getName(): String = mName
    fun setName(name: String) { mName = name }
    fun getSubsystem(): String = mSubsystem
    fun setSubsystem(subsystem: String) { mSubsystem = subsystem}

}