package com.demo.camera;

public class PhotoCamera implements WriteListener{

    private ImageSensor imageSensor;
    private boolean isTurnedOn;
    private boolean savingComplited;
    private Card card;


    public PhotoCamera(ImageSensor imageSensor) {
        this.imageSensor = imageSensor;
        isTurnedOn = false;
        savingComplited = true;
    }

    public PhotoCamera(ImageSensor imageSensor, Card card) {
        this.imageSensor = imageSensor;
        this.card = card;
        isTurnedOn = false;
        savingComplited = true;
    }

    public void turnOn() {
        isTurnedOn = true;
        imageSensor.turnOn();
    }

    public void turnOff() {
        isTurnedOn = false;
            if(savingComplited) {
                imageSensor.turnOff();
            }
    }

    public void pressButton() {
        if(!isTurnedOn){
            //donothing.
        } else {
            savingComplited = false;
            byte[] readData = imageSensor.read();
            card.write(readData);
        }
    }

    @Override
    public void writeCompleted() {
        savingComplited = true;
        if(!isTurnedOn){
            imageSensor.turnOff();
        }
    }
}

