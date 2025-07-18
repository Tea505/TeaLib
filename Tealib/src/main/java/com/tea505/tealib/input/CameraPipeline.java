package com.tea505.tealib.input;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public abstract class CameraPipeline extends OpenCvPipeline {
    public abstract Mat processFrame(Mat input);

}
