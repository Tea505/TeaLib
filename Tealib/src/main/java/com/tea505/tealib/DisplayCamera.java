package com.tea505.tealib;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class DisplayCamera {

    private String deviceName;
    private WebcamName webcamName;
    private OpenCvCamera camera;
    private OpenCvCameraRotation rotation;
    private int width;
    private int height;

    public DisplayCamera(String name) {
        this.deviceName = name;
    }

    public void setDimensions(int width, int height, OpenCvCameraRotation rotation) {
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    public void initialize(HardwareMap hwMap) {
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId",
                "id", hwMap.appContext.getPackageName());

        webcamName = hwMap.get(WebcamName.class, deviceName);
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(width, height, rotation);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        camera.showFpsMeterOnViewport(true);

        if (width == 0 || height == 0 || rotation == null) {
            throw new IllegalArgumentException("Dimensions are not set.");
        }
    }

    public void stopStreaming() {
        camera.stopStreaming();
    }

    public void setPipeline(CameraPipeline pipeline) {
        camera.setPipeline(pipeline);
    }

    public void startStreaming(int width, int height, OpenCvCameraRotation rotation) {
        camera.startStreaming(width, height, rotation);
    }

    public void startStreaming() {
        camera.startStreaming(width, height, rotation);
    }

    public float displayFPS() {
        return camera.getFps();
    }
}
