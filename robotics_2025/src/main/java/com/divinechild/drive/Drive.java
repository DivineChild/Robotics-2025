package com.divinechild.drive;

import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.Constants;
import com.divinechild.motors.Motor;
import com.divinechild.motors.MoveType.DriveModes;

public class Drive {
    private final Motor leftMotor;
    private final Motor rightMotor;
    
    public Drive(FirmataDevice arduino) {
        leftMotor = new Motor(arduino, Constants.DriveMotors.leftDriveID);
        rightMotor = new Motor(arduino, Constants.DriveMotors.rightDriveID);
    }

    public void movePercentOut(long leftPercentOut, long rightPercentOut) {
        leftMotor.move(DriveModes.PercentOut, leftPercentOut);
        rightMotor.move(DriveModes.PercentOut, rightPercentOut);
    }
}
