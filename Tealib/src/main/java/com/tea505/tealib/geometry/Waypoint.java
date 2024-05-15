package com.tea505.tealib.geometry;

/**
 * Represents a waypoint with a type, point, and radius.
 */
public class Waypoint {

    /** The type of the waypoint. */
    private final WaypointType type;

    /** The point of the waypoint. */
    private final Point point;

    /** The radius of the waypoint. */
    private final double radius;

    /**
     * Constructs a waypoint with the specified point and radius.
     *
     * @param point the point of the waypoint
     * @param radius the radius of the waypoint
     */
    public Waypoint(Point point, double radius) {
        this.type = point instanceof Pose2d ? WaypointType.POSE : WaypointType.POINT;
        this.point = point;
        this.radius = radius;
    }

    /**
     * Gets the point of the waypoint.
     *
     * @return the point of the waypoint
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Gets the type of the waypoint.
     *
     * @return the type of the waypoint
     */
    public WaypointType getType() {
        return type;
    }

    /**
     * Gets the radius of the waypoint.
     *
     * @return the radius of the waypoint
     */
    public double getRadius() {
        return radius;
    }
}
