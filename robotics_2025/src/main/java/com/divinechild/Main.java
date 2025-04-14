package com.divinechild;

import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

public class Main {
    public static void main(String[] args) throws IllegalStateException, IOException {
        IODevice arduino = new FirmataDevice(Constants.arduinoPort);

        try {
            arduino.start();
            System.out.println("Board Started, wahoo");

            arduino.ensureInitializationIsDone();
        } catch (Exception e) {
            System.out.println("Error connected: " + e);
        } finally {
            var myLED = arduino.getPin(7);

            myLED.setValue(1);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Sleep error.... someone wasn't tired");
            }
            myLED.setValue(0);
            arduino.stop();
            System.out.println("board stopped");
        }
    }
}