package com.tea505.tealib.vision

import android.graphics.Canvas
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration
import org.opencv.core.Mat

/**
 * Interface for initializing and processing frames in a vision pipeline.
 */
interface PipelineInitializer {

    /**
     * Initializes the pipeline with the given parameters.
     *
     * @param width The width of the input frame.
     * @param height The height of the input frame.
     * @param calibration The camera calibration parameters.
     */
    fun init(width: Int, height: Int, calibration: CameraCalibration)


    /**
     * Processes the input frame and returns the result.
     *
     * @param frame The input frame to process.
     * @param captureTimeNanos The timestamp of the captured frame in nanoseconds.
     * @return The result of processing the frame.
     */
    fun processFrame(frame: Mat, captureTimeNanos: Long ): Any

    /**
     * Draws the processed frame onto the canvas.
     *
     * @param canvas The canvas to draw onto.
     * @param onScreenWidth The width of the canvas in screen pixels.
     * @param onScreenHeight The height of the canvas in screen pixels.
     * @param scaleBmpPxToCanvasPx The scale factor to convert bitmap pixels to canvas pixels.
     * @param scaleCanvasDensity The scale factor for canvas density.
     * @param userContext Additional context provided by the user.
     */
    fun drawFrame(
        canvas: Canvas,
        onScreenWidth: Int,
        onScreenHeight: Int,
        scaleBmpPxToCanvasPx: Float,
        scaleCanvasDensity: Float,
        userContext: Any)
}