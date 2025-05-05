package com.divinechild.Challenges;

import com.divinechild.lights.Light;
import com.divinechild.subsystems.DriveSub;

public class BravoChallenge extends Challenge {

    public BravoChallenge(DriveSub driveSub, Light light1, Light light2, Light light3) {
        super(driveSub, light1, light2, light3);
    }

    public void run() throws InterruptedException {
        start();

        move(20);
        hold(3);

        move(10);
        turn(20);
        hold(3);

        center();
        move(20);
        hold(1);

        halt();
        hold(0.5);

        move(-15);
        hold(6);

        halt();
        hold(0.5);

        move(20);
        hold(1);

        move(10);
        turn(20);
        hold(3);

        center();
        move(20);
        hold(3);

        finish();
    }
    
}
