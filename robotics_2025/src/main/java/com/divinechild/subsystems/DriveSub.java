package com.divinechild.subsystems;

import com.divinechild.motors.Motor;
import com.divinechild.motors.MoveType.DriveModes;

/**Subsystem for driving the car */
public class DriveSub {
    private final Motor driveMotor;
    private final Motor steerMotor;
    
    /**
     * Creates a new DriveSub
     * @param driveMotor - Motor object of the drive motor
     * @param steerMotor - Motor object of the steer motor
     */
    public DriveSub(Motor driveMotor, Motor steerMotor) {
        this.driveMotor = driveMotor;
        this.steerMotor = steerMotor;
    }

    /**
     * Moves the drive motor a percent output 
     * @param percentOut - Value from 1 to -1
     */
    public void movePercentOut(double percentOut) {
        driveMotor.move(DriveModes.PercentOut, percentOut);
    }

    /**
     * Move the servo to a desired angle
     * @param position - Angle to move the servo to
     */
    public void steer(long position) {
        steerMotor.move(DriveModes.Position, position);
    }
}
