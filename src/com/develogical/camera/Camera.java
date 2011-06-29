package com.develogical.camera;

public class Camera implements WriteListener {

    private Sensor sensorOn;
    private MemoryCard memoryCard;
    private boolean isPowerOn = false;
    private boolean pendingWrite = false;
    private byte[] data;

    public Camera(Sensor sensorOn, MemoryCard memoryCard) {
        //To change body of created methods use File | Settings | File Templates.
        this.sensorOn = sensorOn;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {

        if (isPowerOn)
        {
              pendingWrite = true;
              data = sensorOn.readData();
              memoryCard.write(data);
        }

        // not implemented
    }

    public void powerOn() {
        sensorOn.powerUp();
        isPowerOn = true;
    }

    public void powerOff() {
        if (!pendingWrite)
        {
             sensorOn.powerDown();
             isPowerOn = false;
        }
    }

    public void writeComplete() {

        pendingWrite = false;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

