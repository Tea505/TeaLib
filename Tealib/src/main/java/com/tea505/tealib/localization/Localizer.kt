package com.tea505.tealib.localization

import com.tea505.tealib.geometry.Pose2d

interface Localizer {

    var poseEstimate: Pose2d

    fun setPoseEstimate(pose2d: Pose2d)

    fun getWheelPositions(): List<Double>

    fun update()


}