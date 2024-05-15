package com.tea505.tealib.util;

public class MathUtils {

    public static double sine(double radians) {
        return Math.sin(radians);
    }

    public static double cosine(double radians) {
        return Math.cos(radians);
    }

    public static double tangent(double radians) {
        return Math.tan(radians);
    }

    public static double arcSine(double value) {
        return Math.asin(value);
    }

    public static double arcCosine(double value) {
        return Math.acos(value);
    }

    public static double arcTangent(double value) {
        return Math.atan(value);
    }

    public static double absoluteValue(double x) {
        return Math.abs(x);
    }

    public static double squareRoot(double x) {
        return Math.sqrt(x);
    }

    public static double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static double signum(double distance) {
        return Math.signum(distance);
    }

    public static float signum(float distance) {
        return Math.signum(distance);
    }

    public static double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    public static double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    public static double normalizeDegrees(double degrees) {
        degrees %= 360;
        if (degrees < 0) {
            degrees += 360;
        }
        return degrees;
    }

    public static double normalizeRadians(double radians) {
        radians %= (2 * Math.PI);
        if (radians < 0) {
            radians += 2 * Math.PI;
        }
        return radians;
    }

    public static double getRadRotDist(double start, double end) {
        double difference = (end - start + Math.PI) % (2 * Math.PI) - Math.PI;
        return difference < -Math.PI ? (difference + (Math.PI * 2)) : difference;
    }

    public static double getRotDist(double start, double end) {
        return MathUtils.getRadRotDist(start, end);
    }

    public static long factorial(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Input must be non-negative.");
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(x);
    }

    public static double hypot(double x, double y) {
        return Math.hypot(x, y);
    }

}
