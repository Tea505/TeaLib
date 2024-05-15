package com.tea505.tealib.controller;

import com.tea505.tealib.util.MathUtils;

/**
 * Controller implementing Proportional-Integral-Derivative (PID) with feedforward control.
 * Provides methods to calculate control signal based on error, update position, and reset controller state.
 * <p></p>
 * Documentation on how to use this Controller.
 * - Set up PIDCoefficients.
 * - Set target velocity and update feedForwardInput with it.
 * - Measure current velocity of the motor.
 * - Calculate error by subtracting current velocity from target velocity.
 * - Update error but using method.
 * - Calculate signal power by running PIDF method.
 * - Set motor velocity to signal power.
 */
public class PIDFController {

    /** The PID coefficients used for control calculations. */
    private PIDCoefficients coefficients;

    /** The previous error for derivative control. */
    private double previousError;

    /** The current error for control calculations. */
    private double error;

    /** The current position of the system. */
    private double position;

    /** The target position for the system. */
    private double targetPosition;

    /** The integral of the error for integral control. */
    private double errorIntegral;

    /** The derivative of the error for derivative control. */
    private double errorDerivative;

    /** The feedforward input value. */
    private double feedForwardInput;

    /** The time of the previous update in nanoseconds. */
    private long previousUpdateTimeNano;

    /** The time difference between updates in nanoseconds. */
    private long deltaTimeNano;

    /**
     * Constructs a PIDF controller with the specified PID coefficients.
     *
     * @param coefficients the PID coefficients
     */
    public PIDFController(PIDCoefficients coefficients) {
        this.coefficients = coefficients;
        reset();
    }

    /**
     * Calculates the control signal using PIDF control.
     *
     * @return the control signal
     */
    public double runPIDF() {
        return error * getkP() + errorDerivative * getkD() + errorIntegral * getkI() + getkF();
    }

    /**
     * Updates the position of the system and recalculates error, integral, and derivative components.
     *
     * @param update the updated position
     */
    public void updatePosition(double update) {
        position = update;
        previousError = error;
        error = targetPosition - position;

        deltaTimeNano = System.nanoTime() - previousUpdateTimeNano;
        previousUpdateTimeNano = System.nanoTime();

        errorIntegral += error * (deltaTimeNano / MathUtils.power(10.0, 9));
        errorDerivative = (error - previousError) / (deltaTimeNano / MathUtils.power(10.0, 9));
    }

    /**
     * Updates the error directly and recalculates integral and derivative components.
     *
     * @param error the updated error
     */
    public void updateError(double error) {
        previousError = this.error;
        this.error = error;

        deltaTimeNano = System.nanoTime() - previousUpdateTimeNano;
        previousUpdateTimeNano = System.nanoTime();

        errorIntegral += error * (deltaTimeNano / MathUtils.power(10.0, 9));
        errorDerivative = (error - previousError) / (deltaTimeNano / MathUtils.power(10.0, 9));
    }

    /**
     * Updates the feedforward input value.
     *
     * @param input the feedforward input value
     */
    public void updateFeedForwardInput(double input) {
        feedForwardInput = input;
    }

    /**
     * Resets the controller to its initial state.
     */
    public void reset() {
        previousError = 0;
        error = 0;
        position = 0;
        targetPosition = 0;
        errorIntegral = 0;
        errorDerivative = 0;
        previousUpdateTimeNano = System.nanoTime();
    }

    /**
     * Sets the target position for the system.
     *
     * @param set the target position
     */
    public void setTargetPosition(double set) {
        targetPosition = set;
    }

    /**
     * Gets the target position for the system.
     *
     * @return the target position
     */
    public double getTargetPosition() {
        return targetPosition;
    }

    /**
     * Gets the PID coefficients used for control calculations.
     *
     * @return the PID coefficients
     */
    public PIDCoefficients getCoefficients() {
        return coefficients;
    }

    /**
     * Gets the proportional gain (kP) from the PID coefficients.
     *
     * @return the proportional gain
     */
    public double getkP() {
        return coefficients.kP;
    }

    /**
     * Gets the integral gain (kI) from the PID coefficients.
     *
     * @return the integral gain
     */
    public double getkI() {
        return coefficients.kI;
    }

    /**
     * Gets the derivative gain (kD) from the PID coefficients.
     *
     * @return the derivative gain
     */
    public double getkD() {
        return coefficients.kD;
    }

    /**
     * Gets the feedforward gain (kF) from the PID coefficients.
     *
     * @return the feedforward gain
     */
    public double getkF() {
        return coefficients.getFeedForwardCoefficient(feedForwardInput);
    }

    /**
     * Sets the proportional gain (kP) in the PID coefficients.
     *
     * @param kP the proportional gain
     */
    public void setkP(double kP) {
        coefficients.kP = kP;
    }

    /**
     * Sets the integral gain (kI) in the PID coefficients.
     *
     * @param kI the integral gain
     */
    public void setkI(double kI) {
        coefficients.kI = kI;
    }

    /**
     * Sets the derivative gain (kD) in the PID coefficients.
     *
     * @param kD the derivative gain
     */
    public void setD(double kD) {
        coefficients.kD = kD;
    }

    /**
     * Sets the feedforward gain (kF) in the PID coefficients.
     *
     * @param kF the feedforward gain
     */
    public void setF(double kF) {
        coefficients.kF = kF;
    }

    /**
     * Gets the current error.
     *
     * @return the current error
     */
    public double getError() {
        return error;
    }
}
