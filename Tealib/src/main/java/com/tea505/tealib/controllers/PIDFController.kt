package com.tea505.tealib.controllers

import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.sign

class PIDFController(
    private var kP: Double,
    private var kI: Double,
    private var kD: Double,
    private var kF: Double,
) {
    var error: Double = 0.0
    var lastError: Double = 0.0
    var integralSum: Double = 0.0

    var timer: ElapsedTime = ElapsedTime()

    fun setPIDF(kP: Double, kI: Double, kD: Double, kF: Double) {
        this.kP = kP
        this.kI = kI
        this.kD = kD
        this.kF = kF
    }

    fun calculate(target: Double, setPoint: Double): Double {
        error = target - setPoint
        var derivative = (error - lastError) / timer.seconds()
        integralSum += error * timer.seconds()

        var ff = kF * sign(error)
        lastError = error
        timer.reset()

        return (kP * error) + (kI * integralSum) + (kD * derivative) + ff
    }

}