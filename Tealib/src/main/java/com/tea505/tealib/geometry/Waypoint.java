package com.tea505.tealib.geometry;

public class Waypoint {

    private final WaypointType type;
    private final Point point;
    private final double radius;

    public Waypoint(Point point, double radius) {
        this.type = point instanceof Pose2d ? WaypointType.POSE : WaypointType.POINT;
        this.point = point;
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public WaypointType getType() {
        return type;
    }

    public double getRadius() {
        return radius;
    }
}
