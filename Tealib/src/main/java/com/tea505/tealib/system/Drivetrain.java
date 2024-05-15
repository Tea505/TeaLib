package com.tea505.tealib.system;

import com.tea505.tealib.geometry.Pose2d;

/**
 * A Drivetrain interface that your Drivetrain file should implement.
 */
public interface Drivetrain {

    /**
     *  This method sets power to the drivetrain motors using Pose2d.
     *  Can be used for Autonomous and TeleOp both.
     *
     * @param pose2d The positional power supplied to the Drivetrain
     */
    void set(Pose2d pose2d);
}
