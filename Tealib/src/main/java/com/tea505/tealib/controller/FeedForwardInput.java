package com.tea505.tealib.controller;

/**
 * Represents an interface for providing feedforward control inputs.
 */
public interface FeedForwardInput {

    /**
     * Gets the input value for feedforward control.
     *
     * @param input the input value
     * @return the feedforward control input
     */
    double getInput(double input);
}