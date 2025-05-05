package com.divinechild.sonars;

import org.firmata4j.IODevice;
import org.firmata4j.Pin.Mode;

import com.divinechild.PinIO.PinIO;

/** Class for controlling an ultrasonic sensor */
public class Sonar {
    private final PinIO trigIO;
    private final PinIO echoIO;

    /**
     * Sonar for sensing distance
     * @param arduino Object of the arduino
     * @param pinID Port the sonar is plugged into
     */
    public Sonar(IODevice arduino, int trigIO, int echoIO) {
        this.trigIO = new PinIO(arduino, trigIO);
        this.echoIO = new PinIO(arduino, echoIO);

        this.trigIO.setMode(Mode.OUTPUT);
        this.trigIO.setMode(Mode.INPUT);
    }

    /**
     * Get distance detected via trigger and echo
     * @return Distance in centimeters
     */
    public double getDistance() throws InterruptedException {
        // Send 10 µs pulse
        trigIO.setValue(1);
        try {
            Thread.sleep(0, 10000); // 10 µs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        trigIO.setValue(0);
    
        // Timeout after 30 ms if no response
        long timeout = System.nanoTime() + 30_000_000L; // 30 ms in ns
    
        // Wait for echo pin to go HIGH
        while (echoIO.getValue() == 0 && System.nanoTime() < timeout);
    
        if (System.nanoTime() >= timeout) {
            System.err.println("Echo HIGH timeout");
            return -1; // error or out of range
        }
    
        long echoStartTime = System.nanoTime();
    
        // Wait for echo pin to go LOW
        timeout = System.nanoTime() + 30_000_000L;
        while (echoIO.getValue() == 1 && System.nanoTime() < timeout);
    
        if (System.nanoTime() >= timeout) {
            System.err.println("Echo LOW timeout");
            return -1; // error or out of range
        }
    
        long echoEndTime = System.nanoTime();
    
        // Calculate distance
        long duration = echoEndTime - echoStartTime; // nanoseconds
        double microseconds = duration / 1000.0;
        double distance = (microseconds * 0.0343) / 2;
    
        return distance;
    }    

}
