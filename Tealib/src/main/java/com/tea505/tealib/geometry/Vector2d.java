package com.tea505.tealib.geometry;

import com.tea505.tealib.util.MathUtils;

/**
 * Represents a 2D vector with x and y components.
 */
public class Vector2d {

    /** The x-component of the vector. */
    private double x;

    /** The y-component of the vector. */
    private double y;

    /**
     * Constructs a vector with zero components.
     */
    public Vector2d() {
        this(0.0, 0.0);
    }

    /**
     * Constructs a vector with the specified x and y components.
     *
     * @param x the x-component
     * @param y the y-component
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a vector from another vector.
     *
     * @param other the other vector
     */
    public Vector2d(Vector2d other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * Returns the sum of this vector and another vector.
     *
     * @param other the other vector
     * @return the sum of the vectors
     */
    public Vector2d plus(Vector2d other) {
        return new Vector2d(x + other.getX(), y + other.getY());
    }

    /**
     * Returns the difference between this vector and another vector.
     *
     * @param other the other vector
     * @return the difference between the vectors
     */
    public Vector2d minus(Vector2d other) {
        return new Vector2d(x - other.getX(), y - other.getY());
    }

    /**
     * Returns a new vector by dividing this vector by a scalar value.
     *
     * @param scaler the scalar value
     * @return the new vector
     * @throws IllegalArgumentException if the scalar is zero
     */
    public Vector2d div(double scaler) {
        if (scaler == 0) throw new IllegalArgumentException("Cannot be divided by Zero");
        return new Vector2d(getX() / scaler, getY() / scaler);
    }

    /**
     * Returns a new vector by multiplying this vector by a scalar value.
     *
     * @param scaler the scalar value
     * @return the new vector
     */
    public Vector2d mult(double scaler) {
        return new Vector2d(getX() * scaler, getY() * scaler);
    }

    /**
     * Computes the dot product of this vector and another vector.
     *
     * @param other the other vector
     * @return the dot product
     */
    public double dot(Vector2d other) {
        return getX() * other.getX() + getY() * other.getY();
    }

    /**
     * Computes the magnitude of the vector.
     *
     * @return the magnitude
     */
    public double magnitude() {
        return Math.hypot(x, y);
    }

    /**
     * Rotates the vector by a specified angle.
     *
     * @param angle the angle to rotate by
     * @return the rotated vector
     */
    public Vector2d rotate(double angle) {
        return new Vector2d(
                getX() * MathUtils.cosine(angle) - getY() * MathUtils.sine(angle),
                getX() * MathUtils.sine(angle) + getY() * MathUtils.cosine(angle)
        );
    }

    /**
     * Returns the reflection of the vector.
     *
     * @return the reflected vector
     */
    public Vector2d reflect() {
        return new Vector2d(-getX(), -getY());
    }

    /**
     * Returns the absolute value of the components of the vector.
     *
     * @return the absolute value vector
     */
    public Vector2d abs() {
        return new Vector2d(MathUtils.absoluteValue(getX()), MathUtils.absoluteValue(getY()));
    }

    /**
     * Projects this vector onto another vector.
     *
     * @param other the other vector
     * @return the projected vector
     */
    public Vector2d project(Vector2d other) {
        return other.mult(dot(other) / (magnitude() * other.magnitude()));
    }

    /**
     * Returns the x-component of the vector.
     *
     * @return the x-component
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-component of the vector.
     *
     * @return the y-component
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-component of the vector.
     *
     * @param x the x-component to set
     * @return the updated x-component
     */
    public double setX(double x) {
        return this.x = x;
    }

    /**
     * Sets the y-component of the vector.
     *
     * @param y the y-component to set
     * @return the updated y-component
     */
    public double setY(double y) {
        return this.y = y;
    }

    /**
     * Returns a string representation of the vector in the format "x, y".
     *
     * @return a string representation of the vector
     */
    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}