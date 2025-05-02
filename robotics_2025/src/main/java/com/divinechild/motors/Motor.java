package com.divinechild.motors;

import org.firmata4j.IODevice;
import org.firmata4j.Pin.Mode;

import com.divinechild.PinIO.PinIO;
import com.divinechild.motors.MoveType.DriveModes;

/**Class for controlling a motor */
public class Motor {
    private final PinIO motorIO; 

    /**
     * Motor for driving
     * @param arduino Object of the arduino
     * @param pinID Port the motor is plugged into
     */
    public Motor(IODevice arduino, int pinID) {
        this.motorIO = new PinIO(arduino, pinID);
    }


    /**
     * Moves the motor
     * @param type - Type of movement
     * @param value - Value to be passed to the command, will be casted as needed, please use a number
     */
    public void move(DriveModes type, Object value) {
        // Switch statement for moving the motor as needed
        switch (type) {
            case PercentOut:
                movePercentOut((double) value);
                break;
            case Position:
                movePosition((long) value);
                break;
            default:
                break;
        }
    }

    /**
     * Set motor percent output
     * @param percentOut - Value 1 to -1
     */
    private void movePercentOut(double percentOut) {
        setMode(Mode.PWM);
        // 255 is the max percent out, so we multiply the value by that
        motorIO.setValue((long) (percentOut * 180));
    }
    
    /**
     * Set the motor angle (degrees)
     * @param position - Degrees to set the servo to
     */
    private void movePosition(long position) {
        setMode(Mode.SERVO);
        // Set the position of the motor in degrees
        motorIO.setValue(position);
    }

    /** Set the mode of the motor if it is not the correct mode*/
    private void setMode(Mode mode) {
        if (motorIO.getPinMode() != mode) motorIO.setMode(mode);
    }
}
