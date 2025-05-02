package com.divinechild.sonars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.divinechild.Constants;
import com.fazecast.jSerialComm.SerialPort;

public class Sonar {
    private final SerialPort serialPort;
    private final char label;

    /**
     * Sonar for sensing distance
     * @param label Label sent to the arduino to identify sonar selection
     */
    public Sonar(char label) {
        serialPort = SerialPort.getCommPort(Constants.SerialPorts.DEFAULT_PORT);
        serialPort.setBaudRate(9600);
        serialPort.openPort();

        this.label = label;
    }

    /** Retrieves the distance measured by the ultrasonic sensor by reading an outputted line from the Arduino */
    public double getDistance() throws IOException {
        serialPort.getOutputStream().write(label);
        serialPort.getOutputStream().flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        String line = reader.readLine();
        return Double.parseDouble(line); // cm
    }
}
