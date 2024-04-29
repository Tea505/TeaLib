package com.tea505.tealib.system;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;
import java.util.Map;

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

    public boolean isBroken() {
        return !beamBrake.getState();
    }

    @Override
    public void update() {
        isBroken();
    }

    @Override
    public Map<String, Object> getMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("Type", "Beam Brake Sensor");
        metadata.put("Name", name);
        return metadata;
    }
}
