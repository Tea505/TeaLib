package com.tea505.tealib.actions

import java.util.Collections
import java.util.WeakHashMap

abstract class ActionGroupBase: ActionBase(), Action {

    companion object {
        private val groupedActions: MutableSet<Action> =
            Collections.newSetFromMap(WeakHashMap())

        fun registerGroupedActions(vararg actions: Action) {
            groupedActions.addAll(actions)
        }

        fun clearGroupedActions() {
            groupedActions.clear()
        }

        fun clearGroupedActions(action: Action) {
            groupedActions.remove(action)
        }

        fun requireNullGroup(vararg action: Action) {
            requireNullGroup(action.toList())
        }

        fun requireNullGroup(action: Collection<Action>) {
            if (!Collections.disjoint(action, getGroupedActions())) {
                throw IllegalArgumentException("Actions cannot be added to more than one ActionGroup")
            }
        }

        fun getGroupedActions(): Set<Action> {
            return groupedActions
        }
    }


    abstract fun addActions(vararg action: Action)


}