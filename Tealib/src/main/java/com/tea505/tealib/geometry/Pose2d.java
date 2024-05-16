package com.tea505.tealib.geometry;

import com.tea505.tealib.util.MathUtils;

/**
 * Represents a 2D pose with x, y coordinates and a heading.
 */
public class Pose2d extends Point {

    /** The heading of the pose. */
    public double heading;

    /** The tangent vector of the pose. */
    public Vector2d tangent;

    /**
     * Constructs a pose with zero coordinates and heading.
     */
    public Pose2d() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Constructs a pose with the specified x, y coordinates, and heading.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param heading the heading
     */
    public Pose2d(double x, double y, double heading) {
        super(x, y);
        this.heading = MathUtils.normalizeRadians(heading);
    }

    /**
     * Constructs a pose from a point and heading.
     *
     * @param point the point
     * @param heading the heading
     */
    public Pose2d(Point point, double heading) {
        this(point.getX(), point.getY(), heading);
    }

    /**
     * Constructs a pose from a vector and heading.
     *
     * @param vector2d the vector
     * @param headingTan the tangent heading
     */
    public Pose2d(Vector2d vector2d, double headingTan) {
        super(vector2d.getX(), vector2d.getY());
        this.tangent = new Vector2d(MathUtils.cosine(headingTan), MathUtils.sine(headingTan));
    }

    /**
     * Sets the pose to the values of another pose.
     *
     * @param other the other pose
     */
    public void set(Pose2d other) {
        this.setX(other.getX());
        this.setY(other.getY());
        this.heading = other.getHeading();
    }

    /**
     * Returns the sum of this pose and another pose.
     *
     * @param other the other pose
     * @return the sum of the poses
     */
    public Pose2d plus(Pose2d other) {
        return new Pose2d(getX() + other.getX(), getY() + other.getY(), getHeading() + other.getHeading());
    }

    /**
     * Returns the difference between this pose and another pose.
     *
     * @param other the other pose
     * @return the difference between the poses
     */
    public Pose2d minus(Pose2d other) {
        return new Pose2d(getX() - other.getX(), getY() - other.getY(), MathUtils.normalizeRadians(this.getHeading() - other.getHeading()));
    }

    /**
     * Returns the division of this pose by another pose.
     *
     * @param other the other pose
     * @return the division of the poses
     */
    public Pose2d div(Pose2d other) {
        return new Pose2d(this.getX() / other.getX(), this.getX() / other.getY(), this.getHeading() / other.getHeading());
    }

    /**
     * Returns a new pose by multiplying this pose by a scalar value.
     *
     * @param scalar the scalar value
     * @return the new pose
     */
    public Pose2d mult(double scalar){
        return new Pose2d(this.getX() * scalar, this.getY() * scalar, this.getHeading() * scalar);
    }

    public Vector2d toVector2d() {
        return new Vector2d(getX(), getY());
    }

    public double getHeading() {
        return this.heading;
    }

    public Vector2d getTangent() {
        return this.tangent;
    }

    /**
     * Returns a string representation of the pose in the format "x, y, heading".
     *
     * @return a string representation of the pose
     */
    @Override
    public String toString() {
        return getX() + ", " + getY() + ", " + getHeading();
    }
}