package com.tea505.tealib

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ActionSequence {

    private var hasPerform = true
    private var hasConstructed = false
    private var actionList = mutableListOf<MutableList<ActionBase>>()

    fun addAction(action: Action): ActionSequence  {
        actionList.add(mutableListOf(ActionBase(action)))
        return this
    }

    fun addWaitAction(milliseconds: Double): ActionSequence {
        actionList.add(mutableListOf(ActionBase(WaitAction(milliseconds))))
        return this
    }

    fun runParallel(action: Action): ActionSequence {
        if (actionList.isNotEmpty()) {
            actionList.last().add(ActionBase(action))  // Add to the last group for parallel execution
        } else {
            addAction(action)
        }
        return this
    }

    fun construct(): ActionSequence {
        hasConstructed = true
        hasPerform = false
        return this
    }

    /**
    suspend fun perform() {
        if (!hasConstructed) throw IllegalStateException("Call construct() before perform().")

        for (actionBase in actionList) {
            actionList.action.perform()
        }

        hasPerform = true
    } **/

    suspend fun perform() {
        if (!hasConstructed) throw IllegalStateException("Call construct() before perform().")

        for (group in actionList) {  // Sequential execution of groups
            coroutineScope {
                group.map { actionBase ->
                    async { actionBase.perform() }  // Run all actions in the group in parallel
                }.awaitAll()  // Wait for all parallel actions in this group to finish
            }
        }
        hasPerform = true
    }

    fun hasPerformed(): Boolean = hasPerform

    fun reset() {
        hasConstructed = false
        hasPerform = true
        actionList.clear()
    }
}