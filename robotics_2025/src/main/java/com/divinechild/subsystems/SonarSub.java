package com.divinechild.subsystems;

import java.io.IOException;

import com.divinechild.sonars.Sonar;
/**Subsystem for recording distance via ultrasnonic sensors */
public class SonarSub {
    private final Sonar frontSonar;
    private final Sonar rightSonar;
    private final Sonar backSonar;

    /**
     * Creates a new SonarSub
     * @param frontSonar - Sonar object of the front ultrasonic sensor
     * @param rightSonar - Sonar object of the right ultrasonic sensor
     * @param backSonar - Sonar object of the back ultrasonic sensor
     */
    public SonarSub(Sonar frontSonar, Sonar rightSonar, Sonar backSonar) {
        this.frontSonar = frontSonar;
        this.rightSonar = rightSonar;
        this.backSonar = backSonar;
    }

    public double getSonarDistance(Sonar sonar) {
        try {
            // Call getDistance() from the Sonar class
            return sonar.getDistance();
        } catch (IOException e) {
            // Handle the IOException here (e.g., log it or return a default value)
            System.out.println("Error reading front sonar distance: " + e.getMessage());
            return -1.0;  // Return a default value if there was an error
        }
    }

    /** Retrieves the distance from the front of the car to the object detected by front ultrasonic sensor */
    public double getDistanceFront() {
        return getSonarDistance(frontSonar);
    }

    /** Retrieves the distance from the right of the car to the object detected by right ultrasonic sensor */
    public double getDistanceRight() {
        return getSonarDistance(rightSonar);
    }

    /** Retrieves the distance from the back of the car to the object detected by back ultrasonic sensor */
    public double getDistanceBack() {
        return getSonarDistance(backSonar);
    }

}
