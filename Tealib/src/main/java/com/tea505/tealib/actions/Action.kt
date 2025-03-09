package com.tea505.tealib.actions

interface Action {

    suspend fun init()
    suspend fun hasPerform(): Boolean = false
    suspend fun end(interrupt: Boolean)
    suspend fun perform()
}