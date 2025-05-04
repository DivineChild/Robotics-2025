package com.divinechild;

public class Constants {
    public static String arduinoPort = "/dev/ttyACM0";

    public static double speedOfSound = 343.0; /* meters per second */

    public class DriveMotors {
        /**ID of the drive motors */
        public static int driveID = 12;

        /**ID of the steering motor */
        public static int steerID = 10;
        
    }

    public class SonarSensors {
        /**ID of the front ultrasonic sensor */
        public static int frontTrigID = 6;
        public static int frontEchoID = 7;

        /**ID of the right ultrasonic sensor */
        public static int rightTrigID = 8;
        public static int rightEchoID = 9;

        /**ID of the back ultrasonic sensor */
        public static int backTrigID = 10;
        public static int backEchoID = 11;

    }

    // public static class SerialPorts {
    //     // macOS typical pattern
    //     public static final String MAC_HINT = "/dev/tty.usbmodem";

    //     // Linux typical patterns (may be ttyUSB or ttyACM depending on the board)
    //     public static final String LINUX_USB_HINT = "/dev/ttyUSB";
    //     public static final String LINUX_ACM_HINT = "/dev/ttyACM0";

    //     // Windows COM port prefix
    //     public static final String WINDOWS_HINT = "COM";

    //     // Optional: fallback or user-set override
    //     public static final String DEFAULT_PORT = LINUX_ACM_HINT; // set manually if needed
    // }

    public static class Lights {
        public static int light1ID = 8;
        public static int light2ID = 9;
        public static int light3ID = 11;
    }

    public static class Positions {
        public static final int DRIVE_ZERO_SPEED = 90;
        public static final int STEER_CENTER_POSITION = 94;
    }
}
