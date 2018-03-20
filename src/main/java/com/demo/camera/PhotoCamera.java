package com.demo.camera;

public class PhotoCamera {

    private ImageSensor imageSensor;
    private boolean isTurnedOn;

    public PhotoCamera(ImageSensor imageSensor) {
        this.imageSensor = imageSensor;
        isTurnedOn = false;
    }

    public void turnOn() {
        isTurnedOn = true;
        imageSensor.turnOn();
    }

    public void turnOff() {
        isTurnedOn = false;
        imageSensor.turnOff();
    }

    public void pressButton() {
        if(!isTurnedOn){
            //donothing.
        } else {
            imageSensor.read();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

