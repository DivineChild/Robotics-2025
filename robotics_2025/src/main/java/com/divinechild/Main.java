package com.divinechild;

import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.motors.Motor;
import com.divinechild.subsystems.DriveSub;

public class Main {
    private final Motor driveMotor;
    private final Motor steerMotor;

    private final DriveSub driveSub;

    public Main(IODevice arduino) {
        this.driveMotor = new Motor(arduino, Constants.DriveMotors.driveID);
        this.steerMotor = new Motor(arduino, Constants.DriveMotors.steerID);
        
        this.driveSub = new DriveSub(driveMotor, steerMotor);
    }


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