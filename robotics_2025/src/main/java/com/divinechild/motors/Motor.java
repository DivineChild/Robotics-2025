package com.divinechild.motors;

import org.firmata4j.Pin.Mode;
import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.PinIO.PinIO;
import com.divinechild.motors.MoveType.DriveModes;

public class Motor {
    private final PinIO motorIO; 

    /**
     * Motor for driving
     * @param arduino Object of the arduino
     * @param pinID Port the motor is plugged into
     */
    public Motor(FirmataDevice arduino, int pinID) {
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
                motorIO.setMode(Mode.PWM);
                // 255 is the max percent out, so we multiply the value by that
                motorIO.setValue((long) ((double) value * 255));
                break;
        
            case Position:
                motorIO.setValue((long) value);
                break;
            default:
                break;
        }
    }

    public void movePercentOut() {}
}
