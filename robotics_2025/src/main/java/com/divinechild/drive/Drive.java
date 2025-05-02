package com.divinechild.drive;

import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.Constants;
import com.divinechild.motors.Motor;
import com.divinechild.motors.MoveType.DriveModes;

/**Class for Driving the car */
public class Drive {
    private final Motor driveMotor;
    
    /**
     * Creates a new Drive
     * @param arduino - Object of the arduino
     */
    public Drive(FirmataDevice arduino) {
        driveMotor = new Motor(arduino, Constants.DriveMotors.driveID);
    }

    /**
     * Move both motors at the same percent out
     * @param percentOut - Percent out for both motors
     */
    public void movePercentOut(double percentOut) {
        driveMotor.move(DriveModes.PercentOut, percentOut);
    }

}
