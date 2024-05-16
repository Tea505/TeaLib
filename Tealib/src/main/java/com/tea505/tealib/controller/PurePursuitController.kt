package com.tea505.tealib.controller

import com.arcrobotics.ftclib.controller.PIDFController

/**
 * Represents a Pure Pursuit Controller that uses PIDF controllers for x, y, and heading control.
 * Coefficients for the controllers are provided by a PurePursuitCoefficients object.
 */
class PurePursuitController(
    purePursuitCoefficients: PurePursuitCoefficients)
{
    /** The PIDF controller for the x-direction. */
    var xController: PIDFController

    /** The PIDF controller for the y-direction. */
    var yController: PIDFController

    /** The PIDF controller for the heading. */
    var hController: PIDFController

    /**
     * Initializes the PurePursuitController with the provided PurePursuitCoefficients.
     * Constructs PIDF controllers for x, y, and heading using the coefficients.
     *
     * @param purePursuitCoefficients The coefficients for the PIDF controllers.
     */
    init {
        val coefficients = purePursuitCoefficients

        // Initialize the PIDF controllers using coefficients from PurePursuitCoefficients
        xController = PIDFController(coefficients.xP, 0.0, coefficients.xD, 0.0)
        yController = PIDFController(coefficients.yP, 0.0, coefficients.yD, 0.0)
        hController = PIDFController(coefficients.hP, 0.0, coefficients.hD, 0.0)
    }

    /**
     * Calculates the output of the x-controller for a given measured value and set point.
     *
     * @param measuredValue The measured value.
     * @param setPoint The set point.
     * @return The output of the x-controller.
     */
    fun calculateXController(measuredValue: Double, setPoint: Double): Double {
        return xController.calculate(measuredValue, setPoint)
    }

    /**
     * Calculates the output of the y-controller for a given measured value and set point.
     *
     * @param measuredValue The measured value.
     * @param setPoint The set point.
     * @return The output of the y-controller.
     */
    fun calculateYController(measuredValue: Double, setPoint: Double): Double {
        return yController.calculate(measuredValue, setPoint)
    }

    /**
     * Calculates the output of the heading-controller for a given measured value and set point.
     *
     * @param measuredValue The measured value.
     * @param setPoint The set point.
     * @return The output of the heading-controller.
     */
    fun calculateHController(measuredValue: Double, setPoint: Double): Double {
        return hController.calculate(measuredValue, setPoint)
    }
}