package com.tea505.tealib.geometry;

import com.tea505.tealib.util.MathUtils;

public class Vector2d {

    private double x;
    private double y;

    public Vector2d() {
        this(0.0, 0.0);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d other) {
        this.x = other.getX();
        this.y = other.getY();
    }



    public Vector2d plus(Vector2d other) {
        return new Vector2d(x + other.getX(), y + other.getY() );
    }

    public Vector2d minus(Vector2d other) {
        return new Vector2d(x - other.getX(), y - other.getY());
    }

    public Vector2d div(double scaler) {
        if (scaler == 0) throw new IllegalArgumentException("Cannot be divided by Zero");
        return new Vector2d(getX() / scaler, getY() / scaler);
    }

    public Vector2d mult(double scaler) {
        return new Vector2d(getX() * scaler, getY() * scaler);
    }

    public double dot(Vector2d other) {
        return getX() * other.getX() + getY() * other.getY();
    }

    public double magnitude() {
        return Math.hypot(x, y);
    }

    public Vector2d rotate(double angle) {
        return new Vector2d(
                getX() * MathUtils.cosine(angle) - getY() * MathUtils.sine(angle),
                getX() * MathUtils.sine(angle) + getY() * MathUtils.cosine(angle)
        );
    }

    public Vector2d reflect() {
        return new Vector2d(-getX(), -getY());
    }

    public Vector2d abs() {
        return new Vector2d(MathUtils.absoluteValue(getX()), MathUtils.absoluteValue(getY()));
    }

    public Vector2d project(Vector2d other) {
        return other.mult(dot(other) / (magnitude() * other.magnitude()));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double setX(double x) {
        return this.x = x;
    }

    public double setY(double y) {
        return this.y = y;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}
