package com.divinechild.PinIO;

import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;

/**Class for Pin operation, used to handle errors in one place */
public class PinIO {
    private final Pin pin;

    /**
     * Creates a new PinIO
     * @param arduino - Object for the arduino
     * @param pinID - ID of the pin being controlled
     */
    public PinIO (IODevice arduino, int pinID) {
        this.pin = arduino.getPin(pinID);
    }

    /**
     * Create a new PinIO and sets the pin mode
     * @param arduino - Object for the arduino
     * @param pinID - ID of the pin being controlled
     * @param mode - Mode for the pin
     */
    public PinIO (IODevice arduino, int pinID, Pin.Mode mode) {
        this(arduino, pinID);
        setMode(mode);
    }

    /**
     * Write a long value to the pin
     * @param value - Value ot be written to the pin
     */
    public void setValue(long value) {
        try {
            pin.setValue(value);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the current value of the pin.
     * @return 1 if HIGH, 0 if LOW, or -1 if an error occurs
     */
    public long getValue() {
        try {
            return pin.getValue(); // Reads the actual pin state
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isHigh() {
        return pin.getValue() == 1;
    }

    public boolean isLow() {
        return pin.getValue() == 0;
    }

    /**
     * Set the mode of the pin
     * @param mode - Mode of the Pin
     */
    public void setMode(Pin.Mode mode) {
        try {
            pin.setMode(mode);
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current pin mode
     * @return Mode of the pin
     */
    public Mode getPinMode() {
        return pin.getMode();
    }
}
