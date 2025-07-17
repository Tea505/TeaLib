package com.tea505.tealib.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This class encapsulates an encoder attached to a DC motor, providing methods to
 * manage and retrieve the encoder's position.
 */
public class Encoder{

    private DcMotorEx motorEx;
    private Direction direction;
    private double prevPos;

    public Encoder(DcMotorEx motorEx) {
        this.motorEx = motorEx;
        this.direction = Direction.FORWARD;
        this.prevPos = motorEx.getCurrentPosition();
        reset();
    } 

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void reset() {
        motorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        prevPos = motorEx.getCurrentPosition();
        motorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int update() {
        int multiplier = getMultiplier();
        int currentPos = motorEx.getCurrentPosition() * multiplier;
        if (prevPos != currentPos) {
            prevPos = currentPos;
        }
        return currentPos;
    }

    /**
     * Gets the current direction multiplier, taking into account the motor's direction.
     * @return the direction multiplier (1 or -1)
     */
    public int getMultiplier() {
        return getDirection().getMultiplier() * (motorEx.getDirection() == DcMotorSimple.Direction.FORWARD ? 1 : -1);
    }

    public enum Direction {
        FORWARD(1),
        REVERSE(-1);

        private int multiplier;

        Direction(int multiplier) {
            this.multiplier = multiplier;
        }

        public int getMultiplier() {
            return multiplier;
        }
    }
}
