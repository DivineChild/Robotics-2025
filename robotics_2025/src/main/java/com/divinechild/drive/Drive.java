package com.divinechild.drive;

import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.Constants;
import com.divinechild.motors.Motor;
import com.divinechild.motors.MoveType.DriveModes;
import com.divinechild.subsystems.DriveSub;

/**Class for Driving the car */
public class Drive {
    private Motor driveMotor;
    
    /**
     * Creates a new Drive
     * @param arduino - Object of the arduino
     */
    public Drive(IODevice arduino) {
        driveMotor = new Motor(arduino, Constants.DriveMotors.driveID);
    }

    /**
     * Move both motors at the same percent out
     * @param percentOut - Percent out for both motors
     */
    public static void movePercentOut(DriveSub driveSub, double percentOut) {
        driveSub.getDriveMotor().move(DriveModes.PercentOut, percentOut);
    }

    public static void movePosition(DriveSub driveSub, long pose) {
        driveSub.getDriveMotor().move(DriveModes.Position, pose);
    }

    public static void steer(DriveSub driveSub, long pose) {
        driveSub.getSteerMotor().move(DriveModes.Position, pose);
    }

}
