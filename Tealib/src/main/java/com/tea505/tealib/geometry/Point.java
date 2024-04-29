package com.tea505.tealib.geometry;

import com.tea505.tealib.util.MathUtils;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point plus(Point other) {
        return new Point(getX() + other.getX(), getY() + other.getY());
    }

    public Point minus(Point other) {
        return new Point(getX() - other.getY(), getX() - other.getY());
    }

    public Point div(double scaler) {
        if (scaler == 0) throw new IllegalArgumentException("Cannot be divided by Zero");
        return new Point(getX() / scaler, getY() / scaler);
    }

    public Point mult(double scaler) {
        return new Point(getX() * scaler, getY() * scaler);
    }

    public double atan() {
        return Math.atan2(x, y);
    }

    public double radius() {
        return Math.hypot(x, y);
    }

    public Point polar(double r, double a) {
        return new Point(MathUtils.cosine(a) * r, MathUtils.sine(a) * r);
    }

    public Point rotate(double amount) {
        return polar(radius(), atan() + amount);
    }

    public double distanceTo(Point other) {
        return other.minus(this).radius();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}
