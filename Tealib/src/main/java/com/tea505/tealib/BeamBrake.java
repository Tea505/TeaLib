package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a beam brake sensor.
 */
public class BeamBrake implements Sensor {

    /** The digital channel representing the beam brake sensor. */
    public DigitalChannel beamBrake;

    /** The name of the beam brake sensor. */
    public String name;

    /**
     * Constructs a new beam brake sensor with the given name.
     *
     * @param name the name of the beam brake sensor
     */
    public BeamBrake(String name) {
        this.name = name;
    }

    /**
     * Initializes the beam brake sensor.
     *
     * @param hardwareMap the hardware map of the robot
     */
    @Override
    public void initialize(HardwareMap hardwareMap) {
        beamBrake = hardwareMap.get(DigitalChannel.class, name);
        beamBrake.setMode(DigitalChannel.Mode.INPUT);
    }

    /**
     * Checks if the beam brake sensor is broken.
     */
    public void isBroken() {
        beamBrake.getState();
    }

    /**
     * Updates the state of the beam brake sensor.
     */
    @Override
    public void update() {
        isBroken();
    }

    /**
     * Retrieves metadata about the beam brake sensor.
     *
     * @return a map containing metadata about the sensor
     */
    @Override
    public Map<String, Object> getMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("Type", "Beam Brake Sensor");
        metadata.put("Name", name);
        return metadata;
    }
}