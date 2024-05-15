package com.tea505.tealib.localization

import com.tea505.tealib.geometry.Pose2d

/**
 * Interface for robot localization.
 */
interface Localizer {

    /**
     * The estimated pose of the robot.
     */
    var poseEstimate: Pose2d

    /**
     * Sets the estimated pose of the robot.
     *
     * @param pose2d the estimated pose
     */
    fun setPoseEstimate(pose2d: Pose2d)

    /**
     * Returns the positions of the tracking wheels.
     *
     * @return the positions of the tracking wheels
     */
    fun getWheelPositions(): List<Double>

    /**
     * Updates the localization based on sensor inputs.
     */
    fun update()
}