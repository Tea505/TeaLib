package com.tea505.tealib

class FeedForwardController(
    var kS: Double,
    var kG: Double,
    var kV: Double,
    var kA: Double,
) {

    var kP: Double = 0.0;

    constructor(kS: Double, kG: Double, kV: Double, kA: Double, kP: Double)
        : this(kS, kG, kV, kA) {
            this.kP = kP
        }

    // needs more work on this function haven't decided out to properly implement it
    fun calculateServoOutput(theta: Double, vel: Double, accel: Double): Double {
        var output : Double
            = (kS * Math.signum(theta)) + (kG * Math.cos(theta)) + (kV * vel) + (kA * accel)

        // clamp pos
        output = Math.max(0.0, Math.min(1.0, output))
        return output
    }

    // Motor output should be good ill have to test further tho
    fun calculateMotorOutput(vel: Double, accel: Double, posError: Double) : Double {
        val ffPower = (kS * Math.signum(vel)) + (kV * vel) + (kA * accel) + kG
        val posCorrection: Double = kP * posError
        return ffPower + posCorrection
    }
}