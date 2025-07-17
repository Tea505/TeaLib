package com.tea505.tealib.Sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.tea505.tealib.input.Sensor;

/**
 * Class representing a beam brake sensor.
 */
public class BeamBrake implements Sensor {

    public DigitalChannel beamBrake;
    public String name;

    public BeamBrake(String name) {
        this.name = name;
    }

    @Override
    public void initialize(HardwareMap hardwareMap) {
        beamBrake = hardwareMap.get(DigitalChannel.class, name);
        beamBrake.setMode(DigitalChannel.Mode.INPUT);
    }

    public void isBroken() {
        beamBrake.getState();
    }

    @Override
    public void update() {
        isBroken();
    }

}