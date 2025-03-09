package com.tea505.tealib.actions

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

abstract class ActionOpMode: LinearOpMode() {

    open fun reset() { }

    open fun perform() { }

    open fun schedule() { }

    open fun register() { } // TODO: Think about this more

    override fun runOpMode() {
        initialize()

        waitForStart()
        while (!isStopRequested && opModeIsActive()) {
            perform()
        }
        reset()
    }

    abstract fun initialize()

}