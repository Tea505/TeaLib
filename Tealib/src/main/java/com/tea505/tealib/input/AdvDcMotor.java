package com.tea505.tealib.input;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.tea505.tealib.controllers.FeedForwardController;
import com.tea505.tealib.controllers.PIDFController;

public class AdvDcMotor implements DcMotorSimple {

    public DcMotorEx motorEx;
    public FeedForwardController controller;
    public PIDFController pidfController;

    public Double ticksPerRev = null;

    public double currentPosition;
    public double lastTicks;
    public double currentTime;
    public double lastTime;
    public double lastVelocityRadSec;

    public double targetVelocityRadPerSec = 0.0;
    public double targetAccelerationRadPerSecSquared = 0.0;

    public ElapsedTime timer = new ElapsedTime();

    public AdvDcMotor(DcMotorEx motor) {
        motorEx = motor;
        MotorConfigurationType motorConfigurationType = motor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(1.0);
        motor.setMotorType(motorConfigurationType);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller = new FeedForwardController(0, 0, 0, 0);
        pidfController = new PIDFController(0, 0, 0, 0);
    }

    public AdvDcMotor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(DcMotorEx.class, name));
    }

    public void setTicksPerRev(double ticksPerRev) {
        this.ticksPerRev = ticksPerRev;
    }

    public void setFeedForwardConstants(double kV, double kA, double kS, double kG) {
        controller.setCoefficients(kV, kA, kS, kG);
    }

    public void setPIDConstants(double kP, double kI, double kD, double kF) {
        pidfController.setPIDF(kP, kI, kD, kF);
    }

    /**
     * This has to be measured or used in SI units, only works with rad per sec
     */
    public void setTarget(double velocity, double acceleration) {
        this.targetVelocityRadPerSec = velocity;
        this.targetAccelerationRadPerSecSquared = acceleration;
    }

    public void update() {
        if (ticksPerRev == null) {
            throw new IllegalStateException("ticksPerRev is not set. Call setConstants() first.");
        }

        currentTime = timer.seconds();
        currentPosition = motorEx.getCurrentPosition();
        double deltaTicks = currentPosition - lastTicks;
        double deltaTime = currentTime - lastTime;

        if (deltaTime <= 0) return;

        double measuredVelocity  = (deltaTicks / deltaTime) * ( 2 * Math.PI / ticksPerRev);

        double pidPower = pidfController.calculate(targetVelocityRadPerSec, measuredVelocity);
        double feedforward = controller.calculateVertical(targetVelocityRadPerSec, targetAccelerationRadPerSecSquared);
        double totalPower = pidPower + feedforward;

        motorEx.setPower(totalPower);

        lastVelocityRadSec = measuredVelocity;
        lastTime = currentTime;
        lastTicks = currentPosition;
    }

    public void reset() {
        motorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lastTicks = 0;
        lastTime = timer.seconds();
        lastVelocityRadSec = 0;
    }

    public double getPosition() {
        return motorEx.getCurrentPosition();
    }

    public double getVelocity() {
        return motorEx.getVelocity();
    }

    @Override
    public void setDirection(Direction direction) {
        motorEx.setDirection(direction);
    }

    @Override
    public Direction getDirection() {
        return motorEx.getDirection();
    }

    @Override
    public void setPower(double power) {
        motorEx.setPower(power);
    }

    @Override
    public double getPower() {
        return motorEx.getPower();
    }

    @Override
    public Manufacturer getManufacturer() {
        return motorEx.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return motorEx.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return motorEx.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return motorEx.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        motorEx.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        motorEx.close();
    }
}
