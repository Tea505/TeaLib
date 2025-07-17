package com.tea505.tealib.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * A class that can be used for any Controller and converts the Logitech or xbox360 format to Playstation.
 **/
public class Controller {

    public Gamepad gamepad;

    public Controller(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    /**
     * This functions reads and checks the value of a button being pressed.
     **/
    public boolean readButton(Button button) {
        boolean value = false;

        switch (button) {
            case Cross:
                value = gamepad.cross;
                break;
            case Circle:
                value = gamepad.circle;
                break;
            case Triangle:
                value = gamepad.triangle;
                break;
            case Square:
                value = gamepad.square;
                break;
            case Share:
                value = gamepad.share || gamepad.back;
                break;
            case Options:
                value = gamepad.options || gamepad.start;
                break;
            case Right_Stick_Button:
                value = gamepad.right_stick_button;
                break;
            case Left_Stick_Button:
                value = gamepad.left_stick_button;
                break;
            case Right_Bumper:
                value = gamepad.right_bumper;
                break;
            case Left_Bumper:
                value = gamepad.left_bumper;
                break;
            case Dpad_Down:
                value = gamepad.dpad_down;
                break;
            case Dpad_Left:
                value = gamepad.dpad_left;
                break;
            case Dpad_Right:
                value = gamepad.dpad_right;
                break;
            case Dpad_Up:
                value = gamepad.dpad_up;
                break;
            default:
                break;
        }

        return value;
    }

    /**
     *  This function reads and checks the value of a trigger.
     *
     * @param trigger  The Trigger being read
     * @param thresholdMultiplier The Trigger value gets multiplied by this to control certain powers.
     */
    public boolean readTrigger(Button trigger, double thresholdMultiplier) {
        boolean threshold = false;

        if (thresholdMultiplier > 1 || thresholdMultiplier < 0.0) {
            throw new IllegalArgumentException("Multiplier cannot be greater than 1 or less than 0.0");
        }

        switch (trigger) {
            case Left_Trigger:
                if (gamepad.left_trigger * thresholdMultiplier > 0) threshold = true;
               break;
            case Right_Trigger:
                if (gamepad.right_trigger * thresholdMultiplier > 0) threshold = true;
                break;
            default:
                break;
        }

        return threshold;
    }

    public double getRightStickX() {
        return gamepad.right_stick_x;
    }

    public double getRightStickY() {
        return gamepad.right_stick_y;
    }

    public double getLeftStickX() {
        return gamepad.left_stick_x;
    }

    // TODO: Double check later if i need to negate this value
    public double getLeftStickY() {
        return gamepad.left_stick_y;
    }
}
