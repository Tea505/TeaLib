package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.concurrent.TimeUnit;

public class VoltageIterator implements Sensor {

    private VoltageSensor sensor;
    private boolean printWarning = false;

    @Override
    public void initialize(HardwareMap hardwareMap) {
        sensor = hardwareMap.voltageSensor.iterator().next();
    }

    @Override
    public void update() {
        if (getVolt() <= 5.00) {
            System.out.println("CHARGE YOUR BATTERY");
        }
    }

    public double getVolt() {
        return sensor.getVoltage();
    }

    /**
     * Prints out a warning if the voltage reaches specified limits.
     * Throws an exception if the specified voltage limits are invalid.
     *
     * @param voltage    The voltage limit for the warning.
     * @param minVoltage The minimum acceptable voltage limit.
     * @param maxVoltage The maximum acceptable voltage limit.
     */
    public void warningIteration(double voltage, double minVoltage, double maxVoltage) {
        if (voltage > maxVoltage || voltage < minVoltage) {
            throw new IllegalArgumentException("Voltage limit is out of range. " +
                    "Make sure it is between min and max values.");
        }

        /*
        while (getVolt() > voltage) {
            printWarning = false;

            if (getVolt() == voltage) {
                printWarning = true;
                System.out.println("Warning... Voltage limit reached.");

                if (getVolt() < (voltage - 1.0)) {
                    System.out.println("Warning... Voltage is below the limit.");
                    printWarning = true;
                }
            } else {
                System.out.println("Battery levels are fine...");
                printWarning = false;
            }
        }

         */
            double currentVoltage = getVolt();
            if (currentVoltage == voltage && !printWarning) {
                System.out.println("Warning... Voltage limit reached.");

                if (currentVoltage < (voltage - 1.0)) {
                    System.out.println("Warning... Voltage is below the limit.");
                }
                printWarning = true; // Set flag to true to indicate warning is printed
                } else if (currentVoltage != voltage) {
                    System.out.println("Battery levels are fine...");
                    printWarning = false; // Reset flag if voltage goes below the limit
                }

                // Delay for 5 seconds before checking again
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


    }

    public boolean isWarningSent() {
        return printWarning;
    }
}