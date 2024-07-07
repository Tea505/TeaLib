package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Map;

/**
 * A Sensor interface, useful if you are trying to create your own Sensor class.
 * Must implement the class file if used.
 */
public interface Sensor {

    /**
     * Method signature for initializing the sensor
     */
    void initialize(HardwareMap hardwareMap);

    /**
     * Method signature for reading data from the sensor
     */
    void update();

    /**
     * Method signature for retrieving metadata about the sensor
     * @return
     */
    Map<String, Object> getMetadata();

}
