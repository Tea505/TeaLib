package com.tea505.tealib.Sensors;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.tea505.tealib.input.Sensor;

public class Potentiometer implements Sensor {

    private final String sensorName;
    public AnalogInput analogInput;
    public double position;

    public Potentiometer(String sensorName) {
        this.sensorName = sensorName;
    }

    @Override
    public void initialize(HardwareMap hardwareMap) {
        analogInput = hardwareMap.get(AnalogInput.class, sensorName);
    }

    public double getCurrentVoltage() {
        return analogInput.getVoltage();
    }

    @Override
    public void update() {
        double voltage = getCurrentVoltage();
        position = ((voltage / 5.0) * 360) % 360;
    }

    public double getPositionRadians() {
        return Math.toRadians(position);
    }

    public double getPositionDegrees() {
        return Math.toDegrees(getPositionRadians());
    }

    public double getRawPosition() {
        return position;
    }
}
