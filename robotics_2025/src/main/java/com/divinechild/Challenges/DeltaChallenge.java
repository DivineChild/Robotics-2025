package com.divinechild.Challenges;

import com.divinechild.lights.Light;
import com.divinechild.subsystems.DriveSub;
import com.divinechild.subsystems.SonarSub;

public class DeltaChallenge extends Challenge {

    private final SonarSub sonarSub;
    
    public DeltaChallenge(DriveSub driveSub, SonarSub sonarSub, Light light1, Light light2, Light light3) {
        super(driveSub, light1, light2, light3);
        this.sonarSub = sonarSub;
        //TODO Auto-generated constructor stub
    }
    
    public void run() throws Throwable {
        while (true) {
            double frontDistance = sonarSub.getDistanceFront();

            if (frontDistance < 20) {
                forceStop();
                flashLights(10, 0.2);
                break;
            } if (frontDistance < 50) {
                move(-20);
                setLightsBrightness(16);
            } else if (frontDistance < 80) {
                move(-10);
                setLightsBrightness(8);
            } else if (frontDistance < 100) {
                move(-5);
                setLightsBrightness(4);
            } else if (frontDistance < 150) {
                halt();
                setLightsBrightness(0);
            } else if (frontDistance < 170) {
                move(5);
                setLightsBrightness(4);
            } else if (frontDistance < 220) {
                move(10);
                setLightsBrightness(8);
            } else {
                move(20);
                setLightsBrightness(16);
            }

        }
    }
}
