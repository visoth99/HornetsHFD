package org.csc133.a2.gameobjects;
;

public abstract class Movable extends GameObject{
    protected double speed,heading;

    public abstract void move(double acc,int fuel, int water);
}
