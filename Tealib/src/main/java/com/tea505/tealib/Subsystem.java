package com.tea505.tealib;

/**
 * A Subsystem class file if you want to go for a different style.
 */
public abstract class Subsystem {

    /**
     * This method is called periodically, useful for updating specific-Subsystem states.
     */
    public abstract void periodic();

    /**
     * Reads the states of the Subsystem.
     */
    public abstract void read();

    /**
     * Allows the Subsystem to obtain power to perform motion.
     */
    public abstract void update();

    /**
     * Resets all states.
     */
    public abstract void reset();

}
