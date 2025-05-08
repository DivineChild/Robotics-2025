package com.divinechild.Challenges;

import com.divinechild.lights.Light;
import com.divinechild.subsystems.DriveSub;
import com.divinechild.subsystems.SonarSub;

public class FoxTrotChallenge extends Challenge {
    private final SonarSub sonarSub;

    public FoxTrotChallenge(DriveSub driveSub, SonarSub sonarSub, Light light1, Light light2, Light light3) {
        super(driveSub, light1, light2, light3);
        this.sonarSub = sonarSub;
    }

    @Override
    public void run() throws InterruptedException {
        long maxLength;
        start();

        hold(1);

        move(10);



        int counter = 0;

        while (isRunning) {
            double distance = sonarSub.getDistanceRight();

            if (distance == -1 || distance > 200) {
                counter++;
            } else {
                counter = Math.max(counter - 100, 0);
            }

            if (counter > 2000) {
                break;
            }
        }

        hold(0.5);
        halt();
        hold(0.5);

        move(-5);
        hold(0.5);
        turn(25);
        hold(2);
        turn(-25);

        for (int i = 0; i < 4; i++) {
            if (sonarSub.getDistanceBack() < 100 && sonarSub.getDistanceBack() != -1) {
                finish();
                return;
            }

            hold(1);
        }

        halt();




    }

    public void track() {
        
    }
}
