package com.tea505.tealib.system;

public abstract class Subsystem {

    public abstract void periodic();
    public abstract void read();
    public abstract void update();
    public abstract void reset();

}
