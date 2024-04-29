package com.tea505.tealib.controller;

public class PIDCoefficients {

    public double kP;
    public double kI;
    public double kD;
    public double kF;

    public FeedForwardInput feedForwardInput;
    public boolean usingFeedForward;

    public PIDCoefficients() {
        this.kP = 0.0;
        this.kI = 0.0;
        this.kD = 0.0;
        this.kF = 0.0;
    }

    public PIDCoefficients(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }

    public PIDCoefficients(double kP, double kI, double kD, FeedForwardInput kF) {
        usingFeedForward = true;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        feedForwardInput = kF;
    }

    public double getFeedForwardCoefficient(double x) {
        if (!usingFeedForward) return kF;
        return feedForwardInput.getInput(x);
    }
}
