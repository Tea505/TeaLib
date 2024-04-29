package com.tea505.tealib.system;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Robot {

    protected LinearOpMode opMode;

    public abstract void initialize(HardwareMap hardwareMap, Telemetry telemetry);
}
