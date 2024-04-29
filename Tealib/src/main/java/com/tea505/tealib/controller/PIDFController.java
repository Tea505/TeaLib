package com.tea505.tealib.controller;

import com.tea505.tealib.util.MathUtils;

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

    public void setkP(double x) {
        coefficients.kP = x;
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

    public void setkI(double x) {
        coefficients.kI = x;
    }

    public void setD(double x) {
        coefficients.kD = x;
    }

    public void setF(double x) {
        coefficients.kF = x;
    }

    public double getError() {
        return error;
    }
}
