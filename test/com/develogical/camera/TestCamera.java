package com.develogical.camera;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(value = JMock.class)
public class TestCamera {

   Mockery context = new Mockery();
   Sensor sensor = context.mock(Sensor.class);
   MemoryCard memoryCard = context.mock(MemoryCard.class);

   Camera powerCamera = new Camera(sensor, memoryCard);
    private byte[] data =  new byte[1];


    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {

         context.checking(new Expectations() {{
                      exactly(1).of(sensor).powerUp();

         }});

        powerCamera.powerOn();
         // write you test here
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {

         context.checking(new Expectations() {{
                      exactly(1).of(sensor).powerDown();;

         }});

        powerCamera.powerOff();
         // write you test here
    }

    @Test
    public void pressingTheShutterPowerDown() {

        context.checking(new Expectations() {{
              ignoring(sensor) .powerDown();
             never(sensor).readData();

         }});

        powerCamera.powerOff();
        powerCamera.pressShutter();
         // write you test here
    }

     @Test
    public void pressingTheShutterPowerOnCopiesData() {

         context.checking(new Expectations() {{
              ignoring(sensor).powerUp();
             exactly(1).of(sensor).readData(); will(returnValue(data));
             exactly(1).of(memoryCard).write(data);

         }});

        powerCamera.powerOn();
        powerCamera.pressShutter();
         // write you test here
    }

     @Test
    public void writingDataDoNotPowerOffCamera() {

         context.checking(new Expectations() {{
             ignoring(sensor).powerUp();
             exactly(1).of(sensor).readData(); will(returnValue(data));
             exactly(1).of(memoryCard).write(data);
             never(sensor).powerDown();

         }});

        powerCamera.powerOn();
        powerCamera.pressShutter();
        powerCamera.powerOff();
         // write you test here
    }

     @Test
    public void writingDataCompletedDoPowerOffCamera() {

         context.checking(new Expectations() {{
             ignoring(sensor).powerUp();
             exactly(1).of(sensor).readData(); will(returnValue(data));
             exactly(1).of(memoryCard).write(data);
             exactly(1).of(sensor).powerDown();

         }});

        powerCamera.powerOn();
        powerCamera.pressShutter();
        powerCamera.writeComplete();
        powerCamera.powerOff();
         // write you test here
    }

}
