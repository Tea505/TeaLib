package com.tea505.tealib.actions

class ConsecutiveAction
    (vararg actions : Action)
    : ActionGroupBase()
{
        private val toAction = mutableListOf<Action>()
        private var currentActionIndex = -1
        private var performWhenDisabled = true

    init {
        addActions(*actions)
    }

    override fun addActions(vararg action: Action) {
        requireNullGroup(*action)

        if (currentActionIndex != -1) {
            throw IllegalStateException("Actions cannot be added to a ActionGroup while the group is running")
        }

        registerGroupedActions(*action)

        action.forEach { actions ->
            toAction.add(actions)
            mRequirements.addAll(actions.getRequirements())
            performWhenDisabled = performWhenDisabled && actions.performWhenDisabled()
        }
    }

    override fun init() {
        currentActionIndex = 0
        if (toAction.isNotEmpty()) toAction[0].init()
    }

    override fun perform() {
        if (toAction.isEmpty()) return
        val currentAction = toAction[currentActionIndex]

        currentAction.perform()
        if (currentAction.hasPerform()) {
            currentAction.end(false)
            currentActionIndex++
            if (currentActionIndex < toAction.size) {
                toAction[currentActionIndex].init()
            }
        }
    }

    override fun end(interrupt: Boolean) {
        if (interrupt && toAction.isNotEmpty()) {
            toAction[currentActionIndex].end(true)
        }
        currentActionIndex = -1
    }

    override fun hasPerform(): Boolean {
        return currentActionIndex == toAction.size
    }

    override fun performWhenDisabled(): Boolean {
        return performWhenDisabled
    }

}