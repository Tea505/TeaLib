package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This class encapsulates an encoder attached to a DC motor, providing methods to
 * manage and retrieve the encoder's position.
 */
public class Encoder {

    private DcMotorEx motorEx;
    private double currentPos;
    private double prevPos;
    private double posMultiplier;

    public static final double FORWARD = 1;
    public static final double REVERSE = -1;

    /**
     * Constructs an Encoder object attached to the specified motor.
     *
     * @param motorEx the motor to which the encoder is attached
     */
    public Encoder(DcMotorEx motorEx) {
        this.motorEx = motorEx;
        posMultiplier = FORWARD;
        reset();
    }

    /**
     * Sets the direction multiplier for the encoder's position.
     *
     * @param posMultiplier the multiplier to set (use FORWARD or REVERSE constants)
     */
    public void setDirection(double posMultiplier) {
        this.posMultiplier = posMultiplier;
    }

    /**
     * Resets the encoder, setting its current position to zero.
     */
    public void reset() {
        motorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        prevPos = motorEx.getCurrentPosition();
        currentPos = motorEx.getCurrentPosition();
        motorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Updates the encoder's current position.
     */
    public void update() {
        prevPos = currentPos;
        currentPos = motorEx.getCurrentPosition();
    }

    /**
     * Gets the current direction multiplier, taking into account the motor's direction.
     *
     * @return the direction multiplier (1 or -1)
     */
    public double getMultiplier() {
        return posMultiplier * (motorEx.getDirection() == DcMotorSimple.Direction.FORWARD ? 1 : -1);
    }

}
