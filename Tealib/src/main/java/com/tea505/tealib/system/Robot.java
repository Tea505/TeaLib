package com.tea505.tealib.system;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A Robot class that should extend a Robot's hardware file in which you should store all your
 * hardware in which case its easier to import from.
 */

public abstract class Robot {

    /**
     * The Operational Mode circumstance
     */
    protected LinearOpMode opMode;

    /**
     *
     * @param hardwareMap The hardwareMap of your devices.
     * @param telemetry The telemetry display of your devices.
     */
    public abstract void initialize(HardwareMap hardwareMap, Telemetry telemetry);
}
