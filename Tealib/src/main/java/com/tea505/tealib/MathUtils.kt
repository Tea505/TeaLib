package com.tea505.tealib

import kotlin.math.max
import kotlin.math.min

object MathUtils {

    const val PI: Double = 3.141592653589793

    @JvmStatic
    fun getRadRotDist(start: Double, end: Double): Double {
        var difference = (end - start + PI) % (2 * PI) - PI
        if (difference < -PI) {
            difference += PI * 2
        }
        return difference
    }

    @JvmStatic
    fun clip(number: Double, min: Double, max: Double): Double {
        if (number < min) return min
        if (number > max) return max
        return number
    }

    @JvmStatic
    fun clamp(num: Double, min: Double, max: Double): Double {
        return max(min, min(num, max))
    }
}