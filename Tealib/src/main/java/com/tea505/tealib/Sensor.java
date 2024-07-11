package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * A Sensor interface, useful if you are trying to create your own Sensor class.
 */
public interface Sensor {

    void initialize(HardwareMap hardwareMap);

    void update();

}
