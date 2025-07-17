package com.tea505.tealib.actions

import com.tea505.tealib.Subsystem
import java.util.Collections

class ActionSequence {

    companion object {
        @Volatile private var instance: ActionSequence? = null

        @JvmStatic
        @Synchronized
        fun getInstance(): ActionSequence {
            return instance ?: synchronized(ActionSequence::class.java) {
                instance ?: ActionSequence().also { instance = it }
            }
        }
    }

    private val scheduledActions: MutableMap<Action, ActionState> = HashMap()
    private val requirementAction: MutableMap<Subsystem, Action> = HashMap()
    private val subsystems: MutableMap<Subsystem, Action> = HashMap()

    private val initAction: MutableList<(Action) -> Unit> = mutableListOf()
    private val performAction: MutableList<(Action) -> Unit> = mutableListOf()
    private val finishAction: MutableList<(Action) -> Unit> = mutableListOf()
    private val interruptAction: MutableList<(Action) -> Unit> = mutableListOf()

    private val toScheduleAction: MutableMap<Action, Boolean> = HashMap()
    private val toCancelAction: MutableList<Action> = mutableListOf()

    private var disabled: Boolean = false
    private var runInLoop: Boolean = false

    private fun initAction(actions: Action, interruptible: Boolean, requirements: Set<Subsystem>) {
        actions.init()
        val scheduleAction = ActionState(interruptible)
        scheduledActions[actions] = scheduleAction

        initAction.forEach { it(actions) }
        requirements.forEach { requirementAction[it] = actions }

        /**
        for (action in initAction) { action(actions) }
        for (requirement in requirements) { requirementAction[requirement] = actions } **/
    }

    private fun schedule(interruptible: Boolean, action: Action) {
        if (runInLoop) {
            toScheduleAction[action] = interruptible
            return
        }

        /*
        if (ActionGroupBase.getGroupedActions().isEmpty()) {
            throw EmptyActionSequenceException()
        }  */

        if (ActionGroupBase.getGroupedActions().contains(action)) {
            throw IllegalArgumentException(
                "An action that is part of a group cannot be independently scheduled"
            )
        }

        if (disabled || (!action.performWhenDisabled()) || scheduledActions.containsKey(action)) return

        val requirements: Set<Subsystem> = action.getRequirements()

        if (Collections.disjoint(requirementAction.keys,requirements)) {
            initAction(action, interruptible, requirements)
        } else {
            for (requirement in requirements) {
                if (requirementAction.containsKey(requirement) && !scheduledActions[requirementAction[requirement]]!!.isInterruptible()) { return }
            }

            for (requirement in requirements) {
                if (requirementAction.containsKey(requirement)) {
                    cancel(requirementAction[requirement]!!)
                }
            }
            initAction(action, interruptible, requirements)
        }
    }

    fun schedule(interruptible: Boolean, vararg actions: Action) {
        /** for (action: Action in actions) {
            schedule(interruptible, action)
        } **/
        actions.forEach { schedule(interruptible, it) }
    }

    fun schedule(vararg actions: Action) {
        schedule(true, *actions)
    }

    fun perform() {
        if (disabled) return

        for (subsystem: Subsystem in subsystems.keys) subsystem.periodic()

        runInLoop = true
        val iterator = scheduledActions.keys.iterator()
        while (iterator.hasNext()) {
            val actions = iterator.next()

            if (!actions.performWhenDisabled()) { // add robot disabled statement here
                actions.end(true)
                /**
                for (action in interruptAction) {
                    action.invoke(actions)
                } **/
                initAction.forEach { it(actions) }
                requirementAction.keys.removeAll(actions.getRequirements())
                iterator.remove()
                continue
            }

            actions.perform()
            performAction.forEach { it(actions) }
            /**
            for (action in performAction) {
                action.invoke(actions)
            } **/
            if (actions.hasPerform()) {
                actions.end(false)
               /** for (action in finishAction) {
                    action.invoke(actions)
                } **/
                finishAction.forEach { it(actions) }
                iterator.remove()
                requirementAction.keys.removeAll(actions.getRequirements())
            }
        }

        runInLoop = false

        for ((action, interruptible) in toScheduleAction) {
            schedule(interruptible, action)
        }

        for (action: Action in toCancelAction) {
            cancel(action)
        }

        toScheduleAction.clear()
        toCancelAction.clear()

        for ((subsystem, subsystemAction) in subsystems) {
            if (!requirementAction.keys.contains(subsystem)) {
                schedule(subsystemAction)
            }
        }
    }

    fun cancel(vararg actions: Action) {
        if (runInLoop) {

        toCancelAction.addAll(actions)
            return
        }

        for (action1 : Action in actions) {
            if (scheduledActions.containsKey(action1)) {
                continue
            }

            action1.end(true)
           /** for (action in interruptAction) {
                action.invoke(action1)
            } **/
            interruptAction.forEach { it(action1) }
            scheduledActions.remove(action1)
            requirementAction.keys.removeAll(action1.getRequirements())
        }
    }

    fun cancelAll() {
        /** for (action : Action in scheduledActions.keys) {
            cancel(action)
        } **/
        scheduledActions.keys.toList().forEach { cancel(it) }
    }

    fun isScheduled(vararg action: Action): Boolean {
        return scheduledActions.keys.containsAll(action.toList())
    }

    fun activate() { disabled = true }
    fun deactivate() { disabled = false }

    @Synchronized fun reset() { instance = null }

}