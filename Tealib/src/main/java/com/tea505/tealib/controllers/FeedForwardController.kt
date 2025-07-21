package com.tea505.tealib.controllers

import kotlin.math.sign

class FeedForwardController(
    var kV: Double,
    var kA: Double,
    var kS: Double,
    var kG: Double,
) {

    fun setCoefficients(kV: Double,  kA: Double,  kS: Double, kG: Double) {
        this.kV = kV
        this.kA = kA
        this.kS = kS
        this.kG = kG
    }

    fun calculateVertical(velocity: Double, acceleration: Double): Double {
        return (kS * sign(velocity)) + (kV * velocity) + (kA * acceleration) + kG
    }

    fun calculate(velocity: Double): Double {
        return calculateVertical(velocity, 0.0)
    }
}