package com.tea505.tealib.actions

class ParallelAction
    (vararg action: Action) : ActionGroupBase()
{
        private val toAction  = mutableMapOf<Action, Boolean>()
        private var performWhenDisabled = true

    init {
        addActions(*action)
    }

    override fun addActions(vararg action: Action) {
       requireNullGroup(*action)

        if (toAction.containsValue(true)) {
            throw IllegalStateException("Action cannot be added to an ActionGroup while the group is running")
        }

        registerGroupedActions(*action)

        action.forEach { action ->
            if (action.getRequirements().intersect(mRequirements).isNotEmpty()) {
                throw IllegalArgumentException("Multiple actions in a parallel group cannot require the same subsystems")
            }
            toAction[action] = false
            mRequirements.addAll(action.getRequirements())
            performWhenDisabled = performWhenDisabled() && action.performWhenDisabled()
        }
    }

    override fun init() {
        toAction.keys.forEach {
            action: Action -> action.init()
            toAction[action] = true
        }
    }

    override fun perform() {
        toAction.forEach { (action, isPerforming) ->
            if (!isPerforming) {
                return@forEach
            }
            action.perform()
            if (action.hasPerform()) {
                action.end(false)
                toAction[action] = false
            }
        }
    }

    override fun end(interrupt: Boolean) {
        if (interrupt) {
            toAction.forEach { (action, isPerforming) ->
                if (!isPerforming) {
                    action.end(true)
                }
            }
        }
    }

    override fun hasPerform(): Boolean {
        return !toAction.values.contains(true)
    }

    override fun performWhenDisabled(): Boolean {
        return performWhenDisabled
    }

}