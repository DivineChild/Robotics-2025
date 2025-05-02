package com.divinechild.subsystems;

import com.divinechild.motors.Motor;
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

    public Motor getDriveMotor() {
        return driveMotor;
    }

    public Motor getSteerMotor() {
        return steerMotor;
    }
}
