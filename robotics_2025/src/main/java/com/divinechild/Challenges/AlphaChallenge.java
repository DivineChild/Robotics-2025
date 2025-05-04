package com.divinechild.Challenges;

import com.divinechild.lights.Light;
import com.divinechild.subsystems.DriveSub;

public class AlphaChallenge extends Challenge {

    public AlphaChallenge(DriveSub driveSub, Light light1, Light light2, Light light3) {
        super(driveSub, light1, light2, light3);
        //TODO Auto-generated constructor stub
    }

    public void run() throws InterruptedException {
        start();

        move(20);
        hold(2);
        turn(20, 2);
        hold(1);
        turn(-20, 2);
        hold(1);

        halt();
        hold(1);

        move(-10);
        hold(2);
        turn(-20, 4);
        hold(2);
        turn(20, 4);
        hold(4);

        finish();
    }
    
}
