package com.tea505.tealib.geometry;

import com.tea505.tealib.util.MathUtils;

public class Pose2d extends Point {

    private double heading;

    public Pose2d(double x, double y, double heading) {
        super(x, y);
        this.heading = MathUtils.normalizeRadians(heading);
    }

    public Pose2d(Point point, double heading) {
        this(point.getX(), point.getY(), heading);
    }

    public Pose2d(Vector2d vector2d, double heading) {
        this(vector2d.getX(), vector2d.getY(), heading);
    }

    public void set(Pose2d other) {
        this.setX(other.getX());
        this.setY(other.getY());
        this.heading = other.getHeading();
    }

    public Pose2d plus(Pose2d other) {
        return new Pose2d(getX() + other.getX(), getY() + other.getY(), getHeading() + other.getHeading());
    }

    public Pose2d minus(Pose2d other) {
        return new Pose2d(getX() - other.getX(), getY() - other.getY(), MathUtils.normalizeRadians(this.getHeading() - other.getHeading()));
    }

    public Pose2d div(Pose2d other) {
        return new Pose2d(this.getX() / other.getX(), this.getX() / other.getY(), this.getHeading() / other.getHeading());
    }

    public Pose2d mult(double scalar){
        return new Pose2d(this.getX() * scalar, this.getY() * scalar, this.getHeading() * scalar);
    }

    public Vector2d toVector2d() {
        return new Vector2d(getX(), getY());
    }

    public double getHeading() {
        return this.heading;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY() + ", " + getHeading();
    }
}
