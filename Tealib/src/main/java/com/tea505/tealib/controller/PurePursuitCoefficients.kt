package com.tea505.tealib.controller

class PurePursuitCoefficients {

    private var isListBuilt: Boolean = false

    var coeffList: ArrayList<Double> = ArrayList()
        private set
    fun getCoeffList(): ArrayList<Double> {
        return coeffList
    }
    private fun addToCoeffList(value: Double) {
        coeffList.add(value)
    }

    fun clearCoefficients() {
        coeffList.clear()
    }

    var xP: Double = 0.0
        private set

    var xD: Double = 0.0
        private set

    var yP: Double = 0.0
        private set

    var yD: Double = 0.0
        private set

    var hP: Double = 0.0
        private set

    var hD: Double = 0.0
        private set

    var TRANSLATIONAL_SPEED: Double = 0.0
        private set

    var ROTATIONAL_SPEED: Double = 0.0
        private set

    var X_GAIN: Double = 0.0
        private set

    var AllowedMovementError: Double = 0.0
        private set

    var AllowedHeadingError: Double = 0.0
        private set

    fun setxP(xP: Double): PurePursuitCoefficients {
        this.xP = xP
        addToCoeffList(xP)
        return this
    }

    fun setxD(xD: Double): PurePursuitCoefficients {
        this.xD = xD
        addToCoeffList(xD)
        return this
    }

    fun setyP(yP: Double): PurePursuitCoefficients {
        this.yP = yP
        addToCoeffList(yP)
        return this
    }

    fun setyD(yD: Double): PurePursuitCoefficients {
        this.yD = yD
        addToCoeffList(yD)
        return this
    }

    fun setHeadingP(hP: Double): PurePursuitCoefficients {
        this.hP = hP
        addToCoeffList(hP)
        return this
    }

    fun setHeadingD(hD: Double): PurePursuitCoefficients {
        this.hD = hD
        addToCoeffList(hD)
        return this
    }

    fun setTRANSLATIONAL_SPEED(TRANSLATIONAL_SPEED: Double): PurePursuitCoefficients {
        this.TRANSLATIONAL_SPEED = TRANSLATIONAL_SPEED
        addToCoeffList(TRANSLATIONAL_SPEED)
        return this
    }

    fun setROTATIONAL_SPEED(ROTATIONAL_SPEED: Double): PurePursuitCoefficients {
        this.ROTATIONAL_SPEED = ROTATIONAL_SPEED
        addToCoeffList(ROTATIONAL_SPEED)
        return this
    }

    fun setX_GAIN(X_GAIN: Double): PurePursuitCoefficients {
        this.X_GAIN = X_GAIN
        addToCoeffList(X_GAIN)
        return this
    }

    fun setAllowedMovementError(AllowedMovementError: Double): PurePursuitCoefficients {
        this.AllowedMovementError = AllowedMovementError
        addToCoeffList(AllowedMovementError)
        return this
    }

    fun setAllowedHeadingError(AllowedHeadingError: Double): PurePursuitCoefficients {
        this.AllowedHeadingError = AllowedHeadingError
        addToCoeffList(AllowedHeadingError)
        return this
    }

    fun build(): PurePursuitCoefficients {
        isListBuilt = true

        return this
    }

    fun isListBuilt(): Boolean {
        return isListBuilt
    }
}