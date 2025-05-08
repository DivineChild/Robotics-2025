package com.divinechild.subsystems;

import com.divinechild.sonars.Sonar;
/**Subsystem for recording distance via ultrasnonic sensors */
public class SonarSub {
    public final Sonar frontSonar;
    public final Sonar rightSonar;
    public final Sonar backSonar;

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

    public double getSonarDistance(Sonar sonar) throws InterruptedException {
        return sonar.getDistance();
    }

    /** Retrieves the distance from the front of the car to the object detected by front ultrasonic sensor */
    public double getDistanceFront() throws InterruptedException {
        return getSonarDistance(frontSonar);
    }

    /** Retrieves the distance from the right of the car to the object detected by right ultrasonic sensor */
    public double getDistanceRight() throws InterruptedException {
        return getSonarDistance(rightSonar);
    }

    /** Retrieves the distance from the back of the car to the object detected by back ultrasonic sensor */
    public double getDistanceBack() throws InterruptedException {
        return getSonarDistance(backSonar);
    }

}
