package com.divinechild.drive;

import java.io.IOException;

import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.Constants;
import com.divinechild.PinIO.PinIO;

public class Drive {
    private final PinIO leftDrive;
    private final PinIO rightDrive;
    
    public Drive(FirmataDevice arduino) {
        leftDrive = new PinIO(arduino, Constants.DriveMotors.leftDriveID);
        rightDrive = new PinIO(arduino, Constants.DriveMotors.rightDriveID);
    }

    public void movePercentOut(long leftPercentOut, long rightPercentOut) throws IllegalStateException, IOException {
        leftDrive.setValue(leftPercentOut);
        rightDrive.setValue(rightPercentOut);
    }
}
