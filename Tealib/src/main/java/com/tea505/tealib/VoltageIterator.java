package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class VoltageIterator implements Sensor {

    public VoltageSensor sensor;

    @Override
    public void initialize(HardwareMap hardwareMap) {
        sensor = hardwareMap.voltageSensor.iterator().next();
    }

    @Override
    public void update() {
        sensor.getVoltage();
    }

    /**
     * This functions allows users to send out a warning if the specified voltage is reached during an OpMode.
     * Throws an exception if the voltage specified is either too high or too low for measurement.
     */
    public boolean warningIteration(double voltage) {
        double maxVoltage = 14.00;
        double minVoltage = 7.00;

        if (voltage > maxVoltage || voltage < minVoltage) {
            throw new IllegalArgumentException("Voltage required is too high or too low, keep it below 14.00 and above 7.00");
        }

        if (sensor.getVoltage() == voltage) {
            System.out.println("Warning... Voltage limit reached");
            return true;
        } else if (sensor.getVoltage() < voltage) {
            System.out.println("Warning... Voltage is below the limit, please switch out the battery");
            return true;
        } else {
            System.out.println("Battery levels are fine...");
            return false;
        }

    }
}