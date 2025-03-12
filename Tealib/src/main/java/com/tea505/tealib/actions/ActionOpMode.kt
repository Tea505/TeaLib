package com.tea505.tealib.actions

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

abstract class ActionOpMode: LinearOpMode() {

    open fun reset() {
        ActionSequence.getInstance().reset()
    }

    open fun perform() {
        ActionSequence.getInstance().perform()
    }

    open fun schedule(vararg actions: Action) {
        ActionSequence.getInstance().schedule(*actions)
    }

    // open fun register() { } // TODO: imma leave this be cuz why not >_<

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