package com.divinechild.sensory;

import com.divinechild.subsystems.SonarSub;

public class Sense {

    private static SonarSub sonarSub;

    public Sense() {

    }

    public static void setSonarSub(SonarSub ss) {
        sonarSub = ss;
    }

    /** Retrieves the distance from the front of the car to the object detected by front ultrasonic sensor */
    public static double front() throws InterruptedException {
        return sonarSub.getSonarDistance(sonarSub.frontSonar);
    }

    /** Retrieves the distance from the right of the car to the object detected by right ultrasonic sensor */
    public static double right() throws InterruptedException {
        return sonarSub.getSonarDistance(sonarSub.rightSonar);
    }

    /** Retrieves the distance from the back of the car to the object detected by back ultrasonic sensor */
    public static double back() throws InterruptedException {
        return sonarSub.getSonarDistance(sonarSub.backSonar);
    }

}
