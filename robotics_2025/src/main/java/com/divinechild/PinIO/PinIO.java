package com.divinechild.PinIO;

import java.io.IOException;

import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

public class PinIO {
    private final Pin pin;

    public PinIO (FirmataDevice arduino , int pinID) {
        this.pin = arduino.getPin(pinID);
    }

    public void setValue(long value) throws IllegalStateException, IOException {
        pin.setValue(value);
    }
}
