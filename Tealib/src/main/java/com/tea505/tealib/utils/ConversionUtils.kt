package com.tea505.tealib.utils

object ConversionUtils {

    /** Length Conversions  **/
    @JvmStatic
    fun millimetersToCentimeters(millimeters: Double): Double {
        return millimeters / 10
    }

    @JvmStatic
    fun centimetersToMillimeters(centimeters: Double): Double {
        return centimeters * 10
    }

    @JvmStatic
    fun millimetersToInches(millimeters: Double): Double {
        return millimeters / 25.4
    }

    @JvmStatic
    fun inchesToMillimeters(inches: Double): Double {
        return inches * 25.4
    }

    @JvmStatic
    fun inchesToCentiMeters(inches: Double): Double {
        return inches * 2.54
    }

    @JvmStatic
    fun centimetersToInches(centimeters: Double): Double {
        return centimeters / 2.54
    }

    @JvmStatic
    fun degreesToServoPosition(degrees: Double, angleRange: Double): Double {
        return degrees / angleRange
    }

    @JvmStatic
    fun radiansToServoPosition(radians: Double, angleRange: Double): Double {
        val degrees = Math.toDegrees(radians)
        return degreesToServoPosition(degrees, angleRange)
    }

    @JvmStatic
    fun servoPositionToDegrees(position: Double, angleRange: Double): Double {
        if (position < 0 || position > 1) {
            throw IllegalArgumentException("Position must be between 0 and 1.");
        }
        return position * angleRange
    }

    @JvmStatic
    fun servoPositionToRadians(position: Double, angleRange: Double): Double {
        return Math.toRadians(servoPositionToDegrees(position, angleRange))
    }
}