package com.tea505.tealib.controller;

import com.tea505.tealib.util.MathUtils;

/**
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

    private PIDCoefficients coefficients;

    private double previousError;
    private double error;
    private double position;
    private double targetPosition;
    private double errorIntegral;
    private double errorDerivative;
    private double feedForwardInput;

    private long previousUpdateTimeNano;
    private long deltaTimeNano;

    public PIDFController(PIDCoefficients coefficients) {
        this.coefficients = coefficients;
        reset();
    }

    public double runPIDF() {
        return error * getkP() + errorDerivative * getkD() + errorIntegral * getkI() + getkF();
    }

    public void updatePosition(double update) {
        position = update;
        previousError = error;
        error = targetPosition - position;

        deltaTimeNano = System.nanoTime() - previousUpdateTimeNano;
        previousUpdateTimeNano = System.nanoTime();

        errorIntegral += error * (deltaTimeNano / MathUtils.power(10.0, 9));
        errorDerivative = (error - previousError) / (deltaTimeNano / MathUtils.power(10.0, 9));
    }

    public void updateError(double error) {
        previousError = this.error;
        this.error = error;

        deltaTimeNano = System.nanoTime() - previousUpdateTimeNano;
        previousUpdateTimeNano = System.nanoTime();

        errorIntegral += error * (deltaTimeNano / MathUtils.power(10.0, 9));
        errorDerivative = (error - previousError) / (deltaTimeNano / MathUtils.power(10.0, 9));
    }

    public void updateFeedForwardInput(double input) {
        feedForwardInput = input;
    }

    public void reset() {
        previousError = 0;
        error = 0;
        position = 0;
        targetPosition = 0;
        errorIntegral = 0;
        errorDerivative = 0;
        previousUpdateTimeNano = System.nanoTime();
    }

    public void setTargetPosition(double set) {
        targetPosition = set;
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    public PIDCoefficients getCoefficients() {
        return coefficients;
    }
    public double getkP() {
        return coefficients.kP;
    }

    public double getkI() {
        return coefficients.kI;
    }

    public double getkD() {
        return coefficients.kD;
    }

    public double getkF() {
        return coefficients.getFeedForwardCoefficient(feedForwardInput);
    }

    public void setkP(double kP) {
        coefficients.kP = kP;
    }
    public void setkI(double kI) {
        coefficients.kI = kI;
    }

    public void setD(double kD) {
        coefficients.kD = kD;
    }

    public void setF(double kF) {
        coefficients.kF = kF;
    }

    public double getError() {
        return error;
    }
}
