package com.tea505.tealib.system;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Map;

public interface Sensor {

    void initialize(HardwareMap hardwareMap);

    void update();

    Map<String, Object> getMetadata();

}
