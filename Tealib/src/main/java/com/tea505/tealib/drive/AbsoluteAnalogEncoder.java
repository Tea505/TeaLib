package com.tea505.tealib.drive;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class AbsoluteAnalogEncoder {

    private final AnalogInput encoder;
    public static double DEFAULT_RANGE = 3.3;
    public static boolean VALUE_REJECTION = false;
    private double offset;
    private double analogRange;
    private boolean inverted;
    private double pastPosition = 1;

    public AbsoluteAnalogEncoder(AnalogInput enc) {
        this(enc, DEFAULT_RANGE);
    }

    public AbsoluteAnalogEncoder(AnalogInput enc, double AnalogRange) {
        this.encoder = enc;
        this.analogRange = AnalogRange;
        offset = 0;
        inverted = false;
    }

    public AbsoluteAnalogEncoder setOffset(double offset) {
        this.offset = offset;
        return this;
    }

    public AbsoluteAnalogEncoder setInverted(boolean inverted) {
        this.inverted = inverted;
        return this;
    }

    public boolean getDirection() {
        return inverted;
    }

    public double getCurrentPosition() {
        double pos = norm((!inverted ? 1 - getVoltage() / analogRange : getVoltage() / analogRange) * Math.PI * 2 - offset);
        if (!VALUE_REJECTION || Math.abs(normDelta(pastPosition)) > 0.1 || Math.abs(normDelta(pos)) < 1) pastPosition = pos;

        return pastPosition;
    }

    public AnalogInput getEncoder() {
        return encoder;
    }

    public double getVoltage() {
        return encoder.getVoltage();
    }

    private double norm(double angle) {
        double modifiedAngle = angle % 6.283185307179586;
        modifiedAngle = (modifiedAngle + 6.283185307179586) % 6.283185307179586;
        return modifiedAngle;
    }

    private double normDelta(double angleDelta) {
        double modifiedAngleDelta = norm(angleDelta);
        if (modifiedAngleDelta > Math.PI) {
            modifiedAngleDelta -= 6.283185307179586;
        }

        return modifiedAngleDelta;
    }
}
