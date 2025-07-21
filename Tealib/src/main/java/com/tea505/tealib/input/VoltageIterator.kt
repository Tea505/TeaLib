package com.tea505.tealib.input

import com.qualcomm.robotcore.hardware.HardwareMap

class VoltageIterator(hardwareMap: HardwareMap, private val sampleSize: Int = 20) {

    private val sensors = hardwareMap.voltageSensor
    private val readings = ArrayDeque<Double>()

    var averageVoltage: Double = 0.0
        private set

    var lowVoltageThreshold = 11.0 // default

    private var voltageDropListener: (() -> Unit)? = null

    init { update() }

    fun update() {
        val currentVoltage = sensors.minOfOrNull { it.voltage } ?: 0.0
        readings.addFirst(currentVoltage)
        if (readings.size > sampleSize) {
            readings.removeLast()
        }
        averageVoltage = readings.average()
    }

    fun getLatestVoltage(): Double { return readings.firstOrNull() ?: 0.0 }

    fun getMinVoltage(): Double { return readings.minOrNull() ?: 0.0 }

    fun getMaxVoltage(): Double { return readings.maxOrNull() ?: 0.0 }

    fun reset() { readings.clear() }

    fun setVoltageDropListener(listener: () -> Unit) { voltageDropListener = listener }

    fun checkVoltageEvent() { if (isVoltageLow()) { voltageDropListener?.invoke() } }

    fun isVoltageLow(): Boolean = getLatestVoltage() < lowVoltageThreshold

    fun setMinVoltageThreshold(voltageMin: Double) { lowVoltageThreshold = voltageMin }

    fun getMinVoltageThreshold(): Double = lowVoltageThreshold


}