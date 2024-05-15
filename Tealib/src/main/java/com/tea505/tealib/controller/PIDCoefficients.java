package com.tea505.tealib.controller;

/**
 * Represents a set of PID coefficients including proportional, integral, derivative, and feedforward gains.
 */
public class PIDCoefficients {

    /** The proportional gain. */
    public double kP;

    /** The integral gain. */
    public double kI;

    /** The derivative gain. */
    public double kD;

    /** The feedforward gain. */
    public double kF;

    /** The feedforward input function. */
    public FeedForwardInput feedForwardInput;

    /** Indicates whether feedforward control is being used. */
    public boolean usingFeedForward;

    /**
     * Constructs PID coefficients with zero gains.
     */
    public PIDCoefficients() {
        this.kP = 0.0;
        this.kI = 0.0;
        this.kD = 0.0;
        this.kF = 0.0;
    }

    /**
     * Constructs PID coefficients with specified gains.
     *
     * @param kP the proportional gain
     * @param kI the integral gain
     * @param kD the derivative gain
     * @param kF the feedforward gain
     */
    public PIDCoefficients(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }

    /**
     * Constructs PID coefficients with specified gains and feedforward input function.
     *
     * @param kP the proportional gain
     * @param kI the integral gain
     * @param kD the derivative gain
     * @param kF the feedforward input function
     */
    public PIDCoefficients(double kP, double kI, double kD, FeedForwardInput kF) {
        usingFeedForward = true;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        feedForwardInput = kF;
    }

    /**
     * Gets the feedforward coefficient based on the input value.
     *
     * @param x the input value
     * @return the feedforward coefficient
     */
    public double getFeedForwardCoefficient(double x) {
        if (!usingFeedForward) return kF;
        return feedForwardInput.getInput(x);
    }
}
