package com.divinechild.lights;

import org.firmata4j.IODevice;

import com.divinechild.PinIO.PinIO;

/**Class for toggling a light */
public class Light {
    private final PinIO lightIO;
    
    private int state = 0;
    private boolean isFlickering = false;
    private int flickerCounter = 0;

    /**
     * Motor for driving
     * @param arduino Object of the arduino
     * @param pinID Port the light is plugged into
     */
    public Light(IODevice arduino, int pinID) {
        this.lightIO = new PinIO(arduino, pinID);
    }

    /**
     * Turns the light on
     */
    public void turnOn() {
        lightIO.setValue(1);
        isFlickering = false;
    }

    /**
     * Turns the light off
     */
    public void turnOff() {
        lightIO.setValue(0);
        isFlickering = false;
    }

    public void setBrightness(int brightness) {
        if (!isFlickering) {
            state = Math.max(0, Math.min(brightness, 16));
            isFlickering = true;
            flickerCounter = 0;
        } else {
            lightIO.setValue(flickerCounter <= state ? 1 : 0);
            flickerCounter = flickerCounter % 16 + 1;
        }

    }

}
