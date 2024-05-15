package com.tea505.tealib.util;

/**
 * Utility class providing mathematical functions.
 */
public class MathUtils {

    /**
     * Computes the sine of an angle.
     *
     * @param radians the angle in radians
     * @return the sine of the angle
     */
    public static double sine(double radians) {
        return Math.sin(radians);
    }

    /**
     * Computes the cosine of an angle.
     *
     * @param radians the angle in radians
     * @return the cosine of the angle
     */
    public static double cosine(double radians) {
        return Math.cos(radians);
    }

    /**
     * Computes the tangent of an angle.
     *
     * @param radians the angle in radians
     * @return the tangent of the angle
     */
    public static double tangent(double radians) {
        return Math.tan(radians);
    }

    /**
     * Computes the arcsine of a value.
     *
     * @param value the value
     * @return the arcsine of the value
     */
    public static double arcSine(double value) {
        return Math.asin(value);
    }

    /**
     * Computes the arccosine of a value.
     *
     * @param value the value
     * @return the arccosine of the value
     */
    public static double arcCosine(double value) {
        return Math.acos(value);
    }

    /**
     * Computes the arctangent of a value.
     *
     * @param value the value
     * @return the arctangent of the value
     */
    public static double arcTangent(double value) {
        return Math.atan(value);
    }

    /**
     * Computes the absolute value of a number.
     *
     * @param x the number
     * @return the absolute value of the number
     */
    public static double absoluteValue(double x) {
        return Math.abs(x);
    }

    /**
     * Computes the value of the specified base raised to the specified exponent.
     *
     * @param base     the base
     * @param exponent the exponent
     * @return the value of base raised to the exponent
     */
    public static double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Returns the maximum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the maximum of the two integers
     */
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Returns the minimum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the minimum of the two integers
     */
    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    /**
     * Returns the signum function of a distance.
     *
     * @param distance the distance as a double
     * @return the signum function of the distance
     */
    public static double signum(double distance) {
        return Math.signum(distance);
    }

    /**
     * Returns the signum function of a distance.
     *
     * @param distance the distance as a float
     * @return the signum function of the distance
     */
    public static float signum(float distance) {
        return Math.signum(distance);
    }

    /**
     * Converts an angle measured in degrees to radians.
     *
     * @param degrees the angle in degrees
     * @return the angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    /**
     * Converts an angle measured in radians to degrees.
     *
     * @param radians the angle in radians
     * @return the angle in degrees
     */
    public static double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    /**
     * Normalizes an angle measured in degrees to the range [0, 360).
     *
     * @param degrees the angle in degrees
     * @return the normalized angle in degrees
     */
    public static double normalizeDegrees(double degrees) {
        degrees %= 360;
        if (degrees < 0) {
            degrees += 360;
        }
        return degrees;
    }

    /**
     * Normalizes an angle measured in radians to the range [0, 2π).
     *
     * @param radians the angle in radians
     * @return the normalized angle in radians
     */
    public static double normalizeRadians(double radians) {
        radians %= (2 * Math.PI);
        if (radians < 0) {
            radians += 2 * Math.PI;
        }
        return radians;
    }

    /**
     * Computes the angular distance between two angles measured in radians.
     *
     * @param start the start angle in radians
     * @param end   the end angle in radians
     * @return the angular distance between the angles
     */
    public static double getRadRotDist(double start, double end) {
        double difference = (end - start + Math.PI) % (2 * Math.PI) - Math.PI;
        return difference < -Math.PI ? (difference + (Math.PI * 2)) : difference;
    }

    /**
     * Computes the angular distance between two angles measured in degrees.
     *
     * @param start the start angle in degrees
     * @param end   the end angle in degrees
     * @return the angular distance between the angles
     */
    public static double getRotDist(double start, double end) {
        return MathUtils.getRadRotDist(start, end);
    }

    /**
     * Computes the factorial of a non-negative integer.
     *
     * @param n the non-negative integer
     * @return the factorial of the integer
     * @throws IllegalArgumentException if the input is negative
     */
    public static long factorial(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Input must be non-negative.");
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Computes the square root of a number.
     *
     * @param x the number
     * @return the square root of the number
     * @throws IllegalArgumentException if the number is negative
     */
    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(x);
    }

    /**
     * Computes the hypotenuse of a right-angled triangle.
     *
     * @param x the length of one side of the triangle
     * @param y the length of the other side of the triangle
     * @return the length of the hypotenuse
     */
    public static double hypot(double x, double y) {
        return Math.hypot(x, y);
    }

}
