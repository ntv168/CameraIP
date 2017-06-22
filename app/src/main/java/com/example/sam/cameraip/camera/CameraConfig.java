package com.example.sam.cameraip.camera;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Sam on 6/20/2017.
 */

public class CameraConfig {
    private static volatile CameraConfig singletonCamera = null;
    private byte[] faceImage;
    private int faceDetected;
    private BlockingQueue<byte[]> pictureQueue = new LinkedBlockingDeque<>();

    public BlockingQueue<byte[]> getPictureQueue() {
        return pictureQueue;
    }
    public void addPicture(byte[] picture){
        this.pictureQueue.add(picture);
    }

    private CameraConfig() {}

    public static CameraConfig getInstance() {
        if (singletonCamera == null) {
            synchronized (CameraConfig.class){
                if (singletonCamera == null) {
                    singletonCamera = new CameraConfig();

                }
            }
        }
        return singletonCamera;
    }


    public void setFaceImage(byte[] faceImage) {
        this.faceImage = faceImage;
    }
    public byte[] getFaceImage() {
        return faceImage;
    }
    public int getFaceDetected() {
        return faceDetected;
    }
    public void setFaceDetected(int faceDetected) {
        this.faceDetected = faceDetected;
    }
}
