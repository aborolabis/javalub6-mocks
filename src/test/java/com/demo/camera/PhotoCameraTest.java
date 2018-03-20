package com.demo.camera;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;

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
    public void whenCameraIsTurnedOnSensorSendsData(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        PhotoCamera photoCamera = new PhotoCamera (imageSensor);
        photoCamera.turnOn();
        photoCamera.pressButton();

        Mockito.verify(imageSensor).read();
    }

    @Test
    public void whenDataIsCurrentlySavingTurningOffTheCameraDontTurnOffTheSensor(){
        ImageSensor imageSensor = mock(ImageSensor.class);
        PhotoCamera photoCamera = new PhotoCamera (imageSensor);
        photoCamera.turnOn();
        photoCamera.pressButton();
        photoCamera.turnOff();

        
    }
}