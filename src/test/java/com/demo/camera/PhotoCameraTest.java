package com.demo.camera;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class PhotoCameraTest {


    @Test
    public void whenCameraIsTurnedOnTheSensorIsTurningOn(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        PhotoCamera photoCamera = new PhotoCamera(imageSensor);
        photoCamera.turnOn();
        Mockito.verify(imageSensor).turnOn();
    }

    @Test
    public void whenCameraIsTurnedOffTheSensorIsTurnedOff(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        PhotoCamera photoCamera = new PhotoCamera(imageSensor);
        photoCamera.turnOff();
        Mockito.verify(imageSensor).turnOff();
    }

    @Test
    public void whenCameraIsTurnedOffTheButtonDoesNothing(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        PhotoCamera photoCamera = new PhotoCamera (imageSensor);
        photoCamera.pressButton();

        Mockito.verifyZeroInteractions(imageSensor);
    }


    @Test
    public void pressingTheButtonSendsDataFromSensorToCardWhileCameraIsTurnedOn(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        Card card = mock(Card.class);
        PhotoCamera photoCamera = new PhotoCamera (imageSensor, card);
        photoCamera.turnOn();
        photoCamera.pressButton();

        Mockito.verify(imageSensor).read();
        Mockito.verify(card).write(Mockito.any());
    }

    @Test
    public void whileDataIsCurrentlySavingTurningOffTheCameraDontTurnOffTheSensor(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        Card card = mock(Card.class);
        PhotoCamera photoCamera = new PhotoCamera (imageSensor, card);
        photoCamera.turnOn();
        photoCamera.pressButton();
        photoCamera.turnOff();

        Mockito.verify(imageSensor).turnOn();
    }

    @Test
    public void whenDataSavingFinishSensorStopsWorking(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        Card card = mock(Card.class);
        PhotoCamera photoCamera = new PhotoCamera (imageSensor, card);
        photoCamera.turnOn();
        photoCamera.pressButton();
        photoCamera.turnOff();
        photoCamera.writeCompleted();

        Mockito.verify(imageSensor).turnOff();
    }

}
