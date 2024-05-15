package com.tea505.tealib.geometry;

import com.tea505.tealib.util.MathUtils;

/**
 * Represents a point in 2D space with x and y coordinates.
 */
public class Point {

    /** The x-coordinate of the point. */
    private double x;

    /** The y-coordinate of the point. */
    private double y;

    /**
     * Constructs a new point with the specified x and y coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the sum of this point and another point.
     *
     * @param other the other point
     * @return the sum of the points
     */
    public Point plus(Point other) {
        return new Point(getX() + other.getX(), getY() + other.getY());
    }

    /**
     * Returns the difference between this point and another point.
     *
     * @param other the other point
     * @return the difference between the points
     */
    public Point minus(Point other) {
        return new Point(getX() - other.getY(), getX() - other.getY());
    }

    /**
     * Returns a new point by dividing this point by a scalar value.
     *
     * @param scaler the scalar value
     * @return the new point
     * @throws IllegalArgumentException if the scalar is zero
     */
    public Point div(double scaler) {
        if (scaler == 0) throw new IllegalArgumentException("Cannot be divided by Zero");
        return new Point(getX() / scaler, getY() / scaler);
    }

    /**
     * Returns a new point by multiplying this point by a scalar value.
     *
     * @param scaler the scalar value
     * @return the new point
     */
    public Point mult(double scaler) {
        return new Point(getX() * scaler, getY() * scaler);
    }

    /**
     * Computes the arctangent of the point.
     *
     * @return the arctangent of the point
     */
    public double atan() {
        return Math.atan2(x, y);
    }

    /**
     * Computes the radius of the point.
     *
     * @return the radius of the point
     */
    public double radius() {
        return Math.hypot(x, y);
    }

    /**
     * Returns a new point in polar coordinates.
     *
     * @param r the radius
     * @param a the angle
     * @return the new point in polar coordinates
     */
    public Point polar(double r, double a) {
        return new Point(MathUtils.cosine(a) * r, MathUtils.sine(a) * r);
    }

    /**
     * Rotates the point by a specified amount.
     *
     * @param amount the amount to rotate by
     * @return the rotated point
     */
    public Point rotate(double amount) {
        return polar(radius(), atan() + amount);
    }

    /**
     * Computes the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the points
     */
    public double distanceTo(Point other) {
        return other.minus(this).radius();
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x the x-coordinate to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y the y-coordinate to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the point in the format "x, y".
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}