package com.tea505.tealib

@FunctionalInterface
interface Action {
    suspend fun perform()
}