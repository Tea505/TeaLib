package com.tea505.tealib;

import com.tea505.teaplanner.core.geometry.Pose;

/**
 * A Drivetrain interface that your Drivetrain file should implement.
 */
public interface Drivetrain {

    /**
     *  This method sets power to the drivetrain motors using Pose2d.
     *  Can be used for Autonomous and TeleOp both.
     *
     * @param pose The positional power supplied to the Drivetrain
     */
    void set(Pose pose);
}
