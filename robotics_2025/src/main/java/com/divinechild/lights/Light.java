package com.divinechild.lights;

import org.firmata4j.IODevice;

import com.divinechild.PinIO.PinIO;

/**Class for toggling a light */
public class Light {
    private final PinIO lightIO;

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
    }

    /**
     * Turns the light off
     */
    public void turnOff() {
        lightIO.setValue(0);
    }

}
